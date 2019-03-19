package com.datamaster.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamaster.model.Role;
import com.datamaster.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl {

	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		
		this.roleRepository = roleRepository;
	}
	
	public Role findByRole(String name) {
		
		return this.roleRepository.findByName(name);
	}

	public List<?> listAll() {
		List<Role> roles = new ArrayList<>();
		this.roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	public Role save(Role entity) {
		return this.roleRepository.save(entity);
	}
}
