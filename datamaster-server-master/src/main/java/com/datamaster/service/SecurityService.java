package com.datamaster.service;

public interface SecurityService {

	String findLoggedInUser();
	
	void autoLogin(String username, String password);
}
