package com.flight.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight.dto.CommonApiResponse;
import com.flight.entity.User;
import com.flight.exception.UserNameAlredyExistException;
import com.flight.exception.UserNotFoundException;
import com.flight.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    

    public User registerUser(User user) {
    	
    	 Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
    	 if(optionalUser.isPresent()) {
    		throw new UserNameAlredyExistException("User Mail is alredy exists");
    	 }
    		  
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setRole("USER");
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
    	Optional<User> optionalUser = userRepository.findByEmail(email);
    	if(optionalUser.isPresent())
    		return optionalUser.get();
    	else
    		throw new UserNotFoundException("User Not Found");
    }
    
    
}