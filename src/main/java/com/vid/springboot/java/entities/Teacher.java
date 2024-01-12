package com.vid.springboot.java.entities;

import java.util.Date;

import com.vid.springboot.java.dto.TeacherDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Teacher_Table")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private String department;
	private String qualification;
	private Date dob;
	private String address;
	
	
	public TeacherDto getTeacherDto() {
		TeacherDto teacherDto=new TeacherDto();
		teacherDto.setId(id);
		teacherDto.setName(name);
		teacherDto.setDepartment(department);
		teacherDto.setQualification(qualification);
		teacherDto.setGender(gender);
		teacherDto.setDob(dob);
		teacherDto.setDob(dob);
		teacherDto.setAddress(address);
		return teacherDto;
	}
	
}
