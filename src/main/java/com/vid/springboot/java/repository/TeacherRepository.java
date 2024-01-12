package com.vid.springboot.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vid.springboot.java.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
