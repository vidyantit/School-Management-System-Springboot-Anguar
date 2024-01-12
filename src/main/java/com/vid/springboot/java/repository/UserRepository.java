package com.vid.springboot.java.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vid.springboot.java.entities.User;
import com.vid.springboot.java.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserRole(UserRole userRole);

	Optional<User> findFirstByEmail(String email);
	
	User findFirstByEmail(User email);

	List<User> getAllStudentByUserRole(UserRole userRole);

	
}
