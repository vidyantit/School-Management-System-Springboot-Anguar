package com.vid.springboot.java.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TeacherDto {

	private Long id;
	private String name;
	private String gender;
	private String department;
	private String qualification;
	private Date dob;
	private String address;
}
