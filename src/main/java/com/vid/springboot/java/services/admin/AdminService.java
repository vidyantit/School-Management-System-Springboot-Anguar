package com.vid.springboot.java.services.admin;

import java.util.List;

import com.vid.springboot.java.dto.FeeDto;
import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.SingleTeacherDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.dto.TeacherDto;

public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	 List<StudentDto> getAllStudents();

	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);
	
	List<StudentLeaveDto> getAllAppliedLeaves();

	StudentLeaveDto changeLeaveStatus(Long leaveId, String status);

	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);

	SingleTeacherDto getTeacherById(Long teacherId);

	TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);
}
