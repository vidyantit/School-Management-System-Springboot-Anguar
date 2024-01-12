package com.vid.springboot.java.dto;

import java.util.Date;

import com.vid.springboot.java.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class StudentDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String fatherName;
	private String motherName;
	private String studentClass;
	private Date dob;
	private String address;
	private String gender;
	private UserRole role;
}
