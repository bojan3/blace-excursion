package com.blace.excursion.service.impl;

import com.blace.excursion.dto.*;
import com.blace.excursion.emailContexts.ExcursionCancelNotification;
import com.blace.excursion.emailContexts.LocationRequestEmail;
import com.blace.excursion.model.*;
import com.blace.excursion.repository.*;
import com.blace.excursion.service.EmailService;
import com.blace.excursion.service.TourGuideService;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    private ExcursionRepository excursionRepository;
    private LocationRepository locationRepository;
    private TourGuideRepository tourGuideRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;
    private EmailService emailService;
    private RestaurantRepository restaurantRepository;

    private MealRepository mealRepository;
    private LocationApproveTokenRepository locationApproveTokenRepository;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    @Autowired
    public TourGuideServiceImpl(ExcursionRepository excursionRepository, LocationRepository locationRepository,
                                TourGuideRepository tourGuideRepository, UserRepository userRepository,
                                VehicleRepository vehicleRepository, EmailService emailService,
                                LocationApproveTokenRepository locationApproveTokenRepository, RestaurantRepository restaurantRepository,
                                MealRepository mealRepository) {
        this.excursionRepository = excursionRepository;
        this.locationRepository = locationRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.emailService = emailService;
        this.locationApproveTokenRepository = locationApproveTokenRepository;
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }


    @Override
    public Message createExcursion(CreateExcursionDTO createExcursionDTO) throws MessagingException {

//        sendCancelledExcursionNotification(new Date(), "Izlet se otkazuje zbog loših vremenskih uslova.", "bskokic@outlook.com");

        Set<Location> locations = locationRepository.findByIds(createExcursionDTO.getLocationIds());
        Set<Vehicle> vehicles = vehicleRepository.findByIds(createExcursionDTO.getVehicleIds());
        TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());

        if (!areVehiclesAvailable(vehicles, createExcursionDTO.getDate())) {
            return new Message("The vehicle has been taken in the meantime.", false);
        }

        if (!isTourGuideAvailable(tourGuide, createExcursionDTO.getDate())) {
            return new Message("You already have excursion that day.", false);
        }

        // TO-DO real minimum number of persons
        Excursion excursion = new Excursion(createExcursionDTO.getDate(), false, createExcursionDTO.getMinNumberOfPersons(),
                createExcursionDTO.getMaxNumberOfPersons(), createExcursionDTO.getPrice(), tourGuide, locations,
                vehicles);

//        if (createExcursionDTO.getMealId() != null) {
//            Meal meal = mealRepository.getOne(createExcursionDTO.getMealId());
//            excursion.setMeal(meal);
//        }

        Meal meal = mealRepository.getOne(3l);
        excursion.setMeal(meal);

        excursion.setPrice(5000);

        excursionRepository.save(excursion);

        sendLocationMailRequest(excursion.getLocations(), excursion);

        return new Message("Excursion created.", true);
    }

    private boolean areVehiclesAvailable(Set<Vehicle> vehicles, Date excursionDate) {

        Iterator<Vehicle> vehicleIterator = vehicles.iterator();
        while (vehicleIterator.hasNext()) {
            Iterator<Excursion> excursionIterator = vehicleIterator.next().getExcursions().iterator();
            while (excursionIterator.hasNext()) {
                java.sql.Date date = new java.sql.Date(excursionIterator.next().getDate().getTime());
                if (date.equals(excursionDate)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sendLocationMailRequest(Set<Location> locations, Excursion excursion) throws MessagingException {
        Iterator<Location> it = locations.iterator();
        while (it.hasNext()) {
            Location location = it.next();
            if (location.getEmail() != null) {
                LocationRequestEmail mail = new LocationRequestEmail();
                mail.init(location.getEmail());
                mail.setTo(location.getEmail());
                String responseUrl = generateResponseUrl(excursion, location);
                mail.setExcursionInfo(excursion.getDate(), excursion.getMinNumberOfPersons(), excursion.getMaxNumberOfPersons(), responseUrl);
                emailService.sendMail(mail);
            }
        }
    }

    private void sendCancelledExcursionNotification(Date date, String reason, String email) throws MessagingException {

        ExcursionCancelNotification mail = new ExcursionCancelNotification();
        mail.init(email);
        mail.setTo(email);
        mail.setNotificationInfo(date, reason);
        emailService.sendMail(mail);

    }

    private String generateResponseUrl(Excursion excursion, Location location) {
        String token = generateNewToken();
        LocationApproveToken locationApproveToken = new LocationApproveToken(token, excursion, location);
        this.locationApproveTokenRepository.save(locationApproveToken);
        return "http://localhost:4200/location/approve/" + token;
    }

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private boolean isTourGuideAvailable(TourGuide tourGuide, Date excursionDate) throws MessagingException {

        Iterator<Excursion> it = tourGuide.getExcursions().iterator();
        while (it.hasNext()) {
            java.sql.Date date = new java.sql.Date(it.next().getDate().getTime());
            if (date.equals(excursionDate)) {
                return false;
            }
        }
        return true;
    }

    private Vehicle getVehicle(CreateExcursionDTO createExcursionDTO) {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMaxNumberOfPersons() >= createExcursionDTO.getMaxNumberOfPersons()) {
                if (isVehicleAvalible(vehicle, createExcursionDTO.getDate())) {
                    return vehicle;
                }
            }
        }
        return null;
    }

    private Boolean isVehicleAvalible(Vehicle vehicle, Date excursionDate) {
        Iterator<Excursion> it = vehicle.getExcursions().iterator();

        while (it.hasNext()) {
            Date date = new Date(it.next().getDate().getTime());
            if (date.equals(excursionDate)) {
                return false;
            }
        }
        return true;
    }

    private Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        return user.getId();
    }

    @Override
    public List<PastExcursionDTO> getPastExcursions() {
        TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());
        Iterator<Excursion> it = tourGuide.getExcursions().iterator();
        Set<PastExcursion> pastExcursions = new HashSet<PastExcursion>();
        while (it.hasNext()) {
            pastExcursions.addAll(it.next().getPastExcursions());
        }
        return pastExcursionToDTO(pastExcursions);
    }

    private List<PastExcursionDTO> pastExcursionToDTO(Set<PastExcursion> pastExcursions) {
        Iterator<PastExcursion> it = pastExcursions.iterator();
        List<PastExcursionDTO> pastExcursionDTOs = new ArrayList<PastExcursionDTO>();
        while (it.hasNext()) {
            pastExcursionDTOs.add(new PastExcursionDTO(it.next()));
        }
        return pastExcursionDTOs;
    }

    @Override
    public List<TourguideExcursionDTO> getExcursions() {
        List<Excursion> excursions = excursionRepository.getByUserIdNotCancelled(getTourGuideId());
        return excursions.stream().map(excursion -> {
            return new TourguideExcursionDTO(new Date(excursion.getDate().getTime() + 24 * 60 * 60 * 1000), excursion.getApproved(), excursion.getMaxNumberOfPersons(), locationsToString(excursion.getLocations()), mealToString(excursion.getMeal()), excursion.getPrice(), excursion.getReservatedTicketsNum());
        }).collect(Collectors.toList());
    }

    private String mealToString(Meal meal) {
        return meal.getRestaurant().getName() + " " + meal.getName();
    }

    private List<String> locationsToString(Set<Location> locations) {
        List<String> locationNames = new ArrayList<String>();
        Iterator<Location> it = locations.iterator();
        while (it.hasNext()) {
            locationNames.add(it.next().getName());
        }
        return locationNames;
    }

    private Long getTourGuideId() {
        return tourGuideRepository.getByUserId(getUserId()).getId();
    }

    private List<ExcursionDTO> excursionsToDTO(List<Excursion> excursions) {
        List<ExcursionDTO> excursionDTOs = new ArrayList<ExcursionDTO>();
        for (Excursion excursion : excursions) {
            ExcursionDTO excursionDTO = new ExcursionDTO(excursion);
            excursionDTOs.add(excursionDTO);
        }
        return excursionDTOs;
    }

    @Override
    public Boolean cancelExcursion(Long excursionId) {
        Excursion excursion = excursionRepository.getOne(excursionId);
        excursion.setCancelled(true);
        excursionRepository.save(excursion);
        return true;
    }

    @Override
    public List<List<VehicleDTO>> getVehiclesSuggestion(VehicleFilter vehicleFilter) {
        List<List<VehicleDTO>> suggestions = new ArrayList<List<VehicleDTO>>();
        List<Vehicle> vehicles = getAvalibleVehicles(vehicleFilter.getDate());

        for (int i = 1; i <= vehicles.size(); i++) {
            List<List<Vehicle>> possibleSuggestions = Generator.combination(vehicles)
                    .simple(i)
                    .stream()
                    .collect(Collectors.<List<Vehicle>>toList());


            Iterator<List<Vehicle>> suggestionIt = possibleSuggestions.iterator();
            while (suggestionIt.hasNext()) {
                if (calculateNumberOfPersons(suggestionIt.next()) <= vehicleFilter.getMaxNumberOfPersons()) {
                    suggestionIt.remove();
                }
            }
            suggestions.addAll(toDTO(possibleSuggestions));
        }
        return suggestions;
    }

    @Override
    public List<RestaurantDTO> getRestaurants() {
        List<Restaurant> restaurants = this.restaurantRepository.findAll();

        return restaurants.stream().map(restaurant -> {
            return new RestaurantDTO(restaurant.getId(), restaurant.getName(), mealsToDTO(restaurant.getMeals()));
        }).collect(Collectors.toList());
    }

    private List<MealDTO> mealsToDTO(Set<Meal> meals) {
        List<MealDTO> mealDTOS = new ArrayList<MealDTO>();

        Iterator<Meal> it = meals.iterator();
        while (it.hasNext()) {
            Meal meal = it.next();
            mealDTOS.add(new MealDTO(meal.getId(), meal.getName(), meal.getPrice()));
        }

        return mealDTOS;
    }

    private List<List<VehicleDTO>> toDTO(List<List<Vehicle>> vehicleLists) {
        List<List<VehicleDTO>> vehicleListsDTO = new ArrayList<List<VehicleDTO>>();

        Iterator<List<Vehicle>> listIt = vehicleLists.iterator();
        while (listIt.hasNext()) {
            Iterator<Vehicle> vehicleIt = listIt.next().iterator();
            List<VehicleDTO> vehicleDTOs = new ArrayList<VehicleDTO>();
            while (vehicleIt.hasNext()) {
                vehicleDTOs.add(new VehicleDTO(vehicleIt.next()));
            }
            vehicleListsDTO.add(vehicleDTOs);
        }

        return vehicleListsDTO;
    }

    private Integer calculateNumberOfPersons(List<Vehicle> vehicles) {
        Integer capacity = 0;
        Iterator<Vehicle> vehicleIt = vehicles.iterator();
        while (vehicleIt.hasNext()) {
            capacity += vehicleIt.next().getMaxNumberOfPersons();
        }
        return capacity;
    }

    private List<Vehicle> getAvalibleVehicles(Date excursionDate) {
        List<Vehicle> vehicles = this.vehicleRepository.findAll();
        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext()) {
            Vehicle vehicle = it.next();
            if (!isVehicleAvalible(vehicle, excursionDate)) {
                it.remove();
                System.out.println("Zauzeto vozilo: " + vehicle.toString());

            }
        }
        return vehicles;
    }

}
