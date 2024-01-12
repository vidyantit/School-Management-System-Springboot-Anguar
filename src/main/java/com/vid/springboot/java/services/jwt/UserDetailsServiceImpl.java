package com.vid.springboot.java.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vid.springboot.java.entities.User;
import com.vid.springboot.java.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	


	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		//write logic here to get user from db
		
		Optional<User> userOptional=userRepository.findFirstByEmail(email);
		if(userOptional.isEmpty()) throw new UsernameNotFoundException("Username not found",null);
		return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(), 
				userOptional.get().getPassword(), new ArrayList<>());
		
	}
}
