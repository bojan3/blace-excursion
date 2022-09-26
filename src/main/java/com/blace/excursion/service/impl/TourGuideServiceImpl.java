package com.blace.excursion.service.impl;

import java.util.*;

import com.blace.excursion.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.CreateExcursionDTO;
import com.blace.excursion.dto.ExcursionDTO;
import com.blace.excursion.dto.PastExcursionDTO;
import com.blace.excursion.model.Excursion;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.PastExcursion;
import com.blace.excursion.model.TourGuide;
import com.blace.excursion.model.User;
import com.blace.excursion.model.Vehicle;
import com.blace.excursion.repository.ExcursionRepository;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.TourGuideRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.repository.VehicleRepository;
import com.blace.excursion.service.TourGuideService;

@Service
public class TourGuideServiceImpl implements TourGuideService {

    private ExcursionRepository excursionRepository;
    private LocationRepository locationRepository;
    private TourGuideRepository tourGuideRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public TourGuideServiceImpl(ExcursionRepository excursionRepository, LocationRepository locationRepository,
                                TourGuideRepository tourGuideRepository, UserRepository userRepository,
                                VehicleRepository vehicleRepository) {
        this.excursionRepository = excursionRepository;
        this.locationRepository = locationRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Message createExcursion(CreateExcursionDTO createExcursionDTO) {
        Vehicle vehicle = getVehicle(createExcursionDTO);

		if (vehicle == null) {
			return new Message("There is no avaliable vehicle for this excursion.", false);
		}

        Set<Location> locations = locationRepository.findByIds(createExcursionDTO.getLocationIds());

        TourGuide tourGuide = tourGuideRepository.getByUserId(getUserId());

        if (!isTourGuideAvailable(tourGuide, createExcursionDTO.getDate())) {
            return new Message("You already have excursion that day.", false);
        }

        Excursion excursion = new Excursion(createExcursionDTO.getDate(), false,
                createExcursionDTO.getMaxNumberOfPersons(), createExcursionDTO.getPrice(), tourGuide, locations,
                vehicle);

        excursionRepository.save(excursion);
        return new Message("Excursion created.", true);
    }

    private boolean isTourGuideAvailable(TourGuide tourGuide, Date excursionDate) {
        Iterator<Excursion> it = tourGuide.getExcursions().iterator();
        while (it.hasNext()) {
            java.sql.Date date = new java.sql.Date(it.next().getDate().getTime());
            if (date.equals(excursionDate)) {
                return false;
            }
        }

        System.out.println("nema jednakih");
        return true;
    }

    private Vehicle getVehicle(CreateExcursionDTO createExcursionDTO) {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMaxNumberOfPersons() >= createExcursionDTO.getMaxNumberOfPersons()) {
                if (isVehicleAvalible(vehicle, createExcursionDTO)) {
                    return vehicle;
                }
            }
        }
        return null;
    }

    private Boolean isVehicleAvalible(Vehicle vehicle, CreateExcursionDTO createExcursionDTO) {
        Iterator<Excursion> it = vehicle.getExcursions().iterator();

        while (it.hasNext()) {
            java.sql.Date date = new java.sql.Date(it.next().getDate().getTime());
            if (date.equals(createExcursionDTO.getDate())) {
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
    public List<ExcursionDTO> getExcursions() {
        List<Excursion> excursions = excursionRepository.getByUserIdNotCancelled(getTourGuideId());
        return excursionsToDTO(excursions);
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

}
