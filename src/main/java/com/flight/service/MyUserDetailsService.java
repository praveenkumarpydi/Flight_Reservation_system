package com.flight.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flight.entity.User;
import com.flight.exception.UserNotFoundException;
import com.flight.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = fetchUserbyEmail(username);
		return new MyUserDetails(user);
	}
	
	public User fetchUserbyEmail(String username) {
		 Optional<User> optionalUser = userRepo.findByEmail(username);
		                             
		 System.out.println("User being Find");
		 System.out.println(optionalUser.isPresent());
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User is Not Present With this mail");
		}
			return optionalUser.get();
	}

}
