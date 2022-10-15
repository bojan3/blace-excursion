package com.blace.excursion.service.impl;

import com.blace.excursion.dto.*;
import com.blace.excursion.model.*;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.TourGuideRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.repository.VehicleRepository;
import com.blace.excursion.service.AdminService;
import com.blace.excursion.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private TourGuideRepository tourGuideRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;
    private LocationRepository locationRepository;

    @Autowired
    public AdminServiceImpl(TourGuideRepository tourGuideRepository, RoleService roleService,
                            PasswordEncoder passwordEncoder, UserRepository userRepository, VehicleRepository vehicleRepository,
                            LocationRepository locationRepository) {
        this.tourGuideRepository = tourGuideRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Boolean createTourGuide(UserRequest userRequest) {
        List<Role> roles = roleService.findByName("ROLE_TOURGUIDE");
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        User user = new User(userRequest, roles);
        this.userRepository.save(user);
        return true;
    }

    @Override
    public Boolean createVehicle(CreateVehicleDTO createVehicleDTO) {
        Vehicle vehicle = new Vehicle(createVehicleDTO);
        this.vehicleRepository.save(vehicle);
        return true;
    }

    @Override
    public Boolean createLocation(CreateLocationDTO createLocationDTO) {
        Location location = new Location(createLocationDTO);
        this.locationRepository.save(location);
        return true;
    }

    @Override
    public List<UserDTO> getTourGuides() {
        List<TourGuide> tourguides = this.tourGuideRepository.findAllNotDeleted();
        return this.tourgouidesToDTO(tourguides);
    }

    private List<UserDTO> tourgouidesToDTO(List<TourGuide> tourguides) {
        List<UserDTO> tourguideDTOs = new ArrayList<>();
        Iterator<TourGuide> it = tourguides.iterator();
        while (it.hasNext()) {
            UserDTO tourguide = new UserDTO(it.next().getUser());
            tourguideDTOs.add(tourguide);
        }
        return tourguideDTOs;
    }

    @Override
    public List<LocationDTO> getLocations() {
        List<Location> locations = this.locationRepository.findAllNotDeleted();
        return this.locationsToDTO(locations);
    }

    private List<LocationDTO> locationsToDTO(List<Location> locations) {
        List<LocationDTO> locationDTOs = new ArrayList<>();
        Iterator<Location> it = locations.iterator();
        while (it.hasNext()) {
            locationDTOs.add(new LocationDTO(it.next()));
        }
        return locationDTOs;
    }

    @Override
    public List<VehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAllNotDeleted();
        return this.vehiclesToDTO(vehicles);
    }

    private List<VehicleDTO> vehiclesToDTO(List<Vehicle> vehicles) {
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext()) {
            vehicleDTOs.add(new VehicleDTO(it.next()));
        }
        return vehicleDTOs;
    }

    @Override
    public void deleteTourGuide(Long tourguideId) {
        TourGuide tourGuide = tourGuideRepository.getOne(tourguideId);
        tourGuide.setDeleted(true);
        tourGuide.getUser().setEnabled(false);
        this.tourGuideRepository.save(tourGuide);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.getOne(vehicleId);
        vehicle.setDeleted(true);
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteLocation(Long locationId) {
        Location location = locationRepository.getOne(locationId);
        location.setDeleted(true);
        this.locationRepository.save(location);
    }

}
