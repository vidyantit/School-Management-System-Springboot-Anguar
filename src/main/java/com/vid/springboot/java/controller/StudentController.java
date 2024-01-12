package com.vid.springboot.java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.services.student.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

	
	private final StudentService studentService;
	
	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto=studentService.getStudentById(studentId);
		if(singleStudentDto==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(singleStudentDto);
		}
	
	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto){
		StudentLeaveDto submittedStudentLeaveDto=studentService.applyLeave(studentLeaveDto);
		if(submittedStudentLeaveDto==null) 
			return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentLeaveDto);
	}
	
	@GetMapping("/leave/{studentId}")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeavesByStudentId(@PathVariable Long studentId){
		List<StudentLeaveDto> studentLeaveDtos=studentService.getAllAppliedLeavesByStudentId(studentId);
		if(studentLeaveDtos==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(studentLeaveDtos);
		}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){
		 StudentDto createdStudentDto=studentService.updateStudent(studentId,studentDto);
		 if(createdStudentDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
		 }
	
	
	
}
