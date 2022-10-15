package com.blace.excursion.service;

import com.blace.excursion.dto.UserRequest;
import com.blace.excursion.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User save(UserRequest userRequest);
}
