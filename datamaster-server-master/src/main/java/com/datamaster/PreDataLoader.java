package com.datamaster;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.datamaster.model.Role;
import com.datamaster.model.User;
import com.datamaster.repository.RoleRepository;
import com.datamaster.repository.UserRepository;
import com.datamaster.service.RoleServiceImpl;
import com.datamaster.service.UserServiceImpl;

@Component
public class PreDataLoader implements ApplicationListener<ApplicationReadyEvent> {
	
	private UserServiceImpl userService;
	private RoleServiceImpl roleService;
	
	private static final Logger logger = LogManager.getLogger(PreDataLoader.class);
	
	@Autowired
	public PreDataLoader(UserRepository userRepository, 
						 RoleRepository roleRepository,
						 BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userService = new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder);
		this.roleService = new RoleServiceImpl(roleRepository);
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.debug("Loading the prepared dataset for testing ...");
		//loadRoles();
		//loadUsers();
	}
	
	@SuppressWarnings("unused")
	private void loadRoles() {
		
		Role admin, user;
		
		admin = new Role();
		admin.setId((long) -1);
		admin.setName("ADMIN");
		this.roleService.save(admin);
		
		user = new Role();
		user.setId((long) -1);
		user.setName("USER");
		this.roleService.save(user);
	}
	
	@SuppressWarnings("unused")
	private void loadUsers() {
		
		User admin, user;
		
		admin = new User();
		admin.setId((long) -1);
		admin.setFirstName("San");
		admin.setLastName("Zheng");
		admin.setEmail("zs@dm");
		admin.setPassword("123456");
		admin.setActive(true);
		this.userService.save(admin);
		
		user = new User();
		user.setId((long) -1);
		user.setFirstName("Si");
		user.setLastName("Li");
		user.setEmail("ls@dm");
		user.setPassword("123456");
		user.setActive(true);
		this.userService.save(user);
		
		User user1 = new User();
		user1.setId((long) -1);
		user1.setFirstName("Wu");
		user1.setLastName("Wang");
		user1.setEmail("ww@dm");
		user1.setPassword("123456");
		user1.setActive(true);
		this.userService.save(user1);
	}

}
