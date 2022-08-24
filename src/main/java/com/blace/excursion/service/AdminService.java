package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.CreateLocationDTO;
import com.blace.excursion.dto.CreateVehicleDTO;
import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.dto.UserRequest;

public interface AdminService {

	Boolean createTourGuide(UserRequest userRequest);

	Boolean createVehicle(CreateVehicleDTO createVehicleDTO);

	Boolean createLocation(CreateLocationDTO createLocationDTO);

}
