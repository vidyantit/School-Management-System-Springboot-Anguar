package com.vid.springboot.java.configuration;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.vid.springboot.java.dto.JwtRequest;
import com.vid.springboot.java.dto.JwtResponse;
import com.vid.springboot.java.repository.UserRepository;
import com.vid.springboot.java.security.JwtHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ResponseBody
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtHelper jwtHelper;
	
    public static final String TOKEN_PREFIX="Bearer";
    public static final String HEADER_STRING="Authorization";
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody JwtRequest jwtRequest,HttpServletResponse response,HttpServletRequest request) throws Exception,JsonEOFException
	{
	try{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));
		
	}catch(BadCredentialsException e) {
		throw new BadCredentialsException("Incorrect username or password");
		
	}catch(DisabledException disabledException) {
		response.sendError(HttpServletResponse.SC_NOT_FOUND,"User not created");
		return;
	}

	final UserDetails userDetails=userDetailsService.loadUserByUsername(jwtRequest.getEmail());
	java.util.Optional<com.vid.springboot.java.entities.User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
	final String jwt=jwtHelper.generateToken(userDetails.getUsername());
	if(optionalUser.isPresent()) {
		response.getWriter().write(new JSONObject()
		                    .put("userId", optionalUser.get().getId())
		                    .put("role", optionalUser.get().getUserRole()).toString());
		
	}
	response.setHeader("Access-Control-Expose-Headers", "Authorization");
	response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Control-Type,Accept,X-Custom-header");
	response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
	
     // return new JwtResponse(jwt);
}
}
