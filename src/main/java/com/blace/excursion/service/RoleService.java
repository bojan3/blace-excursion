package com.blace.excursion.service;

import com.blace.excursion.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);

    List<Role> findByName(String name);
}
