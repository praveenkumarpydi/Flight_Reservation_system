package com.flight.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flight.entity.User;

@Service
public class AuthService {

	public User getAuthenticedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		if(authentication == null || !authentication.isAuthenticated())
			return null;
		MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
		return userDetails.getUser();
	}
}
