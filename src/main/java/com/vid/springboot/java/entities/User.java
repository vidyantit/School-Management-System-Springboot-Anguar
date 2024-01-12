package com.vid.springboot.java.entities;

import java.util.Date;

import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="User_Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public UserRole userRole;
	
	public StudentDto getStudentDto() {
		StudentDto studentDto=new StudentDto();
		studentDto.setId(id);
		studentDto.setName(fatherName);
		studentDto.setEmail(email);
		studentDto.setPassword(password);
		studentDto.setFatherName(fatherName);
		studentDto.setMotherName(motherName);
		studentDto.setStudentClass(studentClass);
		studentDto.setDob(dob);
		studentDto.setAddress(address);
		studentDto.setGender(gender);
		return studentDto;
	}
	
}
