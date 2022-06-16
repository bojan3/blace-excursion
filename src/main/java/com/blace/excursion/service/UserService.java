package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.model.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
}
