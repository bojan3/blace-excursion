package com.blace.excursion.service;

import java.util.List;

import com.blace.excursion.model.Role;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
