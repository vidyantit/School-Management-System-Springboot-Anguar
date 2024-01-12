package com.vid.springboot.java.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.enums.StudentLeaveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data

@Table(name="Leave_Table")
public class StudentLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;
	private String body;
	private Date date;
	private StudentLeaveStatus studentLeaveStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	public StudentLeaveDto getStudentLeaveDto() {
		StudentLeaveDto studentLeaveDto=new StudentLeaveDto();
		studentLeaveDto.setId(id);
		studentLeaveDto.setSubject(subject);
		studentLeaveDto.setBody(body);
		studentLeaveDto.setDate(date);
		studentLeaveDto.setStudentLeaveStatus(studentLeaveStatus);
		studentLeaveDto.setUserId(user.getId());
		return studentLeaveDto;
	}
}
