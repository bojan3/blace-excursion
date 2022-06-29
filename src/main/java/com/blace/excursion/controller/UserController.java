package com.blace.excursion.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blace.excursion.dto.UserDTO;
import com.blace.excursion.model.User;
import com.blace.excursion.service.UserService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")	
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	@PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'TOURGUIDE')")
	public UserDTO user(Principal user) {
		return new UserDTO(this.userService.findByUsername(user.getName()));
	}
}
