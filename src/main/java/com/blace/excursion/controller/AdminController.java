package com.blace.excursion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blace.excursion.dto.CreateLocationDTO;
import com.blace.excursion.dto.CreateVehicleDTO;
import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.dto.UserRequest;
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
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@PostMapping("/vehicle")
	public ResponseEntity<Boolean> createVehicle(@RequestBody CreateVehicleDTO createVehicleDTO) {
		Boolean created = this.adminService.createVehicle(createVehicleDTO);
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@PostMapping("/location")
	public ResponseEntity<Boolean> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
		Boolean created = this.adminService.createLocation(createLocationDTO);
		return new ResponseEntity<>(created, HttpStatus.OK);
	}
}
