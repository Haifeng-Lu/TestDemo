package com.datamaster.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datamaster.model.User;
import com.datamaster.repository.RoleRepository;
import com.datamaster.repository.UserRepository;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;

public class UserServiceTest {

	@Mock
	private UserRepository mockUserRepository;
	
	@Mock
	private RoleRepository mockRoleRepository;
	
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	
	private UserServiceImpl userServiceUnderTest;
	private User user;
	
	@Before
	public void setUp() {
		
		initMocks(this);
		
		userServiceUnderTest = new UserServiceImpl(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
		user = new User();
		user.setId((long) 1);
		user.setFirstName("Jianbo");
		user.setLastName("Zheng");
		user.setEmail("jianbo.zheng@gmail.com");
		user.setActive(true);
		
		Mockito.when(mockUserRepository.save(any())).thenReturn(user);
		Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
	}
	
	@Test
	public void testFindUserByEmail() {
		// Setup
		final String email = "jianbo.zheng@gmail.com";
		
		// Run the test
		final User result = userServiceUnderTest.findUserByEmail(email);
		
		// Verify the result
		assertEquals(email, result.getEmail());
	}
	
	
}
