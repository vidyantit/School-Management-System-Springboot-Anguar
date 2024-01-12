package com.vid.springboot.java.dto;

import java.util.Date;

import com.vid.springboot.java.enums.StudentLeaveStatus;

import lombok.Data;

@Data
public class StudentLeaveDto {

	private Long id;
	private String subject;
	private String body;
	private Date date;
	private StudentLeaveStatus studentLeaveStatus;
	private Long userId;
}
