package com.vid.springboot.java.services.student;

import java.util.List;

import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long studentId);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);
	
	List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

}
