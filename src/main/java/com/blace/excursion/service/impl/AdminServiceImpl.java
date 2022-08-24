package com.blace.excursion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.CreateLocationDTO;
import com.blace.excursion.dto.CreateVehicleDTO;
import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.model.Location;
import com.blace.excursion.model.Role;
import com.blace.excursion.model.User;
import com.blace.excursion.model.Vehicle;
import com.blace.excursion.repository.LocationRepository;
import com.blace.excursion.repository.TourGuideRepository;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.repository.VehicleRepository;
import com.blace.excursion.service.AdminService;
import com.blace.excursion.service.RoleService;

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

}
