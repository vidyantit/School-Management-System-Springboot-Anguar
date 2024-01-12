package com.vid.springboot.java.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vid.springboot.java.dto.User;
import com.vid.springboot.java.services.admin.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUser() {
		System.out.println("This is user");
		return userService.getAllUser();
	}
	
	@GetMapping("/currentUser")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
}
