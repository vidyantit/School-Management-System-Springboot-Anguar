package com.vid.springboot.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vid.springboot.java.entities.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

}
