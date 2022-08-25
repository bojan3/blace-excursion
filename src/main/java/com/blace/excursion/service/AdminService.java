package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.CreateLocationDTO;
import com.blace.excursion.dto.CreateVehicleDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.dto.VehicleDTO;

public interface AdminService {

	Boolean createTourGuide(UserRequest userRequest);

	Boolean createVehicle(CreateVehicleDTO createVehicleDTO);

	Boolean createLocation(CreateLocationDTO createLocationDTO);

	List<UserDTO> getTourGuides();

	List<LocationDTO> getLocations();

	List<VehicleDTO> getVehicles();

	void deleteTourGuide(Long tourguideId);

	void deleteVehicle(Long vehicleId);

	void deleteLocation(Long locationId);
}
