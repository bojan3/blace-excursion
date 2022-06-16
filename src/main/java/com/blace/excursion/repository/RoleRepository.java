package com.blace.excursion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blace.excursion.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
