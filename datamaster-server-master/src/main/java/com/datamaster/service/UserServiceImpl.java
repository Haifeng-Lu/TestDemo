package com.datamaster.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.datamaster.model.Role;
import com.datamaster.model.User;
import com.datamaster.repository.RoleRepository;
import com.datamaster.repository.UserRepository;

@Service("userService")
public class UserServiceImpl  {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, 
					   RoleRepository roleRepository, 
					   BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User findUserByEmail(String email) {
		
		return this.userRepository.findByEmail(email);
	}

	public List<?> listAll() {
		List<User> users = new ArrayList<>();
		this.userRepository.findAll().forEach(users::add);
		return users;
	}

	public User save(User user) {
		if(user.getPassword() != null) {
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		}
		user.setActive(true);
		//user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setRoles(new HashSet<Role>(this.roleRepository.findAll()));
		return userRepository.save(user);
	}
}
