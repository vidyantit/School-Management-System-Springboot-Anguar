package com.vid.springboot.java.services.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vid.springboot.java.dto.User;





@Service

public class UserService {

	ArrayList<User> store=new ArrayList<>();

	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"Vidyant Singh","vidyant@java.com"));
		store.add(new User(UUID.randomUUID().toString(),"soni Singh","soni@java.com"));
		store.add(new User(UUID.randomUUID().toString(),"sunil Singh","sunil@java.com"));
		store.add(new User(UUID.randomUUID().toString(),"john Singh","john@java.com"));
		
	}
	
	public List<User> getAllUser(){
		return this.store;
	}
	
	
}
