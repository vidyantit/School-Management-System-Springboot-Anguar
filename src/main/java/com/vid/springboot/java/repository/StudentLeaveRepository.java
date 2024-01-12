package com.vid.springboot.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.vid.springboot.java.entities.StudentLeave;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long> {

	List<StudentLeave> findAllByUserId(Long studentId);

}
