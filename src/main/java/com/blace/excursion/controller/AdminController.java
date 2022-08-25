package com.blace.excursion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blace.excursion.dto.CreateLocationDTO;
import com.blace.excursion.dto.CreateVehicleDTO;
import com.blace.excursion.dto.LocationDTO;
import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.dto.VehicleDTO;
import com.blace.excursion.service.AdminService;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

	private AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/tourguide")
	public ResponseEntity<Boolean> createTourGuide(@RequestBody UserRequest userRequest) {
		Boolean created = this.adminService.createTourGuide(userRequest);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/tourguides")
	public ResponseEntity<List<UserDTO>> getTourGuides() {
		List<UserDTO> tourguides = this.adminService.getTourGuides();
		return new ResponseEntity<>(tourguides, HttpStatus.OK);
	}

	@DeleteMapping("/tourguide/{tourguideId}")
	public ResponseEntity<Boolean> deleteTourGuide(@PathVariable Long tourguideId) {
		this.adminService.deleteTourGuide(tourguideId);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/vehicle")
	public ResponseEntity<Boolean> createVehicle(@RequestBody CreateVehicleDTO createVehicleDTO) {
		Boolean created = this.adminService.createVehicle(createVehicleDTO);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/vehicles")
	public ResponseEntity<List<VehicleDTO>> getVehicles() {
		List<VehicleDTO> vehicles = this.adminService.getVehicles();
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
	}

	@DeleteMapping("/vehicle/{vehicleId}")
	public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long vehicleId) {
		this.adminService.deleteVehicle(vehicleId);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/location")
	public ResponseEntity<Boolean> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
		Boolean created = this.adminService.createLocation(createLocationDTO);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/locations")
	public ResponseEntity<List<LocationDTO>> getLocations() {
		List<LocationDTO> locationDTOs = adminService.getLocations();
		return new ResponseEntity<>(locationDTOs, HttpStatus.OK);
	}

	@DeleteMapping("/location/{locationId}")
	public ResponseEntity<Boolean> deleteLocation(@PathVariable Long locationId) {
		this.adminService.deleteLocation(locationId);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}
}
