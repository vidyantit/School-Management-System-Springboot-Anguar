package com.vid.springboot.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vid.springboot.java.dto.FeeDto;
import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.SingleTeacherDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.dto.TeacherDto;
import com.vid.springboot.java.services.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
		 StudentDto createdStudentDto=adminService.postStudent(studentDto);
		 if(createdStudentDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
		 }
	
	@GetMapping("/students")
       public ResponseEntity<List<StudentDto>> getAllStudents(){
    	   List<StudentDto> allStudents=adminService.getAllStudents();
    	   return ResponseEntity.ok(allStudents);
       }
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
		adminService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto=adminService.getStudentById(studentId);
		if(singleStudentDto==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(singleStudentDto);
		}
	
	@PutMapping("/updateStudent/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){
		 StudentDto createdStudentDto=adminService.updateStudent(studentId,studentDto);
		 if(createdStudentDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
		 }
	
	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> payFee(@PathVariable Long studentId, @RequestBody FeeDto feeDto){
		FeeDto paidFeeDto=adminService.payFee(studentId,feeDto);
		 if(paidFeeDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(paidFeeDto);
	}
	
	
	@GetMapping("/leaves")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaves(){
		List<StudentLeaveDto> studentLeaveDtos=adminService.getAllAppliedLeaves();
		if(studentLeaveDtos==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(studentLeaveDtos);
		}
	
	
	@GetMapping("/leave/{leaveId}/{status}")
	public ResponseEntity<?> changeLeaveStatus(@PathVariable Long leaveId,@PathVariable String status){
		StudentLeaveDto studentLeaveDto=adminService.changeLeaveStatus(leaveId,status);
		if(studentLeaveDto==null) 
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(studentLeaveDto);
		}
	
	//Teacher Operations
	
	@PostMapping("/teacher")
	public ResponseEntity<?> postTeacher(@RequestBody TeacherDto teacherDto){
		 TeacherDto createdTeacherDto=adminService.postTeacher(teacherDto);
		 if(createdTeacherDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacherDto);
		 }
	
	@GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getAllTeachers(){
 	   List<TeacherDto> allTeachers=adminService.getAllTeachers();
 	   return ResponseEntity.ok(allTeachers);
    }
	

	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId){
		adminService.deleteTeacher(teacherId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<SingleTeacherDto> getTeacherById(@PathVariable Long teacherId){
		SingleTeacherDto singleTeacherDto=adminService.getTeacherById(teacherId);
		if(singleTeacherDto==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(singleTeacherDto);
		}
	
	
	@PutMapping("/updateTeacher/{teacherId}")
	public ResponseEntity<?> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDto teacherDto){
		TeacherDto createdTeacherDto=adminService.updateTeacher(teacherId,teacherDto);
		 if(createdTeacherDto==null) 
			 return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
			 return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacherDto);
		 }
	
	
	}
	
	
	
	
	
