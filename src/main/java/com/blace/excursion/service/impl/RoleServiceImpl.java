package com.blace.excursion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blace.excursion.model.Role;
import com.blace.excursion.repository.RoleRepository;
import com.blace.excursion.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findById(Long id) {
    Role auth = this.roleRepository.getOne(id);
    return auth;
  }

  @Override
  public List<Role> findByName(String name) {
	List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }


}
