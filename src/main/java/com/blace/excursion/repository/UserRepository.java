package com.blace.excursion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blace.excursion.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

