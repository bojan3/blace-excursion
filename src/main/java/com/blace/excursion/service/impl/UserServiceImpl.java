package com.blace.excursion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.model.Role;
import com.blace.excursion.model.User;
import com.blace.excursion.repository.UserRepository;
import com.blace.excursion.service.RoleService;
import com.blace.excursion.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

	@Override
	public User save(UserRequest userRequest) {
		User user = new User();
		user.setUsername(userRequest.getUsername());
		
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEnabled(true);
		user.setEmail(userRequest.getEmail());
		user.setPhoneNumber(userRequest.getPhoneNumber());

		List<Role> roles = roleService.findByName("ROLE_CLIENT");
		user.setRoles(roles);
		
		return this.userRepository.save(user);
	}

}
