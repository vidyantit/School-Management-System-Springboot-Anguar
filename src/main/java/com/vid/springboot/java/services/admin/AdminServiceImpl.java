package com.vid.springboot.java.services.admin;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vid.springboot.java.dto.FeeDto;
import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.SingleTeacherDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.dto.TeacherDto;
import com.vid.springboot.java.entities.Fee;
import com.vid.springboot.java.entities.StudentLeave;
import com.vid.springboot.java.entities.Teacher;
import com.vid.springboot.java.entities.User;
import com.vid.springboot.java.enums.StudentLeaveStatus;
import com.vid.springboot.java.enums.UserRole;
import com.vid.springboot.java.repository.FeeRepository;
import com.vid.springboot.java.repository.StudentLeaveRepository;
import com.vid.springboot.java.repository.TeacherRepository;
import com.vid.springboot.java.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	
	private final UserRepository userRepository;
	
	private final FeeRepository feeRepository;
	
	private final StudentLeaveRepository studentLeaveRepository;
	
	private final TeacherRepository teacherRepository;

	
	

	

	@PostConstruct
	public void createAdminAcount() {
		
		User adminAcount=userRepository.findByUserRole(UserRole.ADMIN);
		if(adminAcount==null) {
		User admin=new User();
		admin.setEmail("admin@test.com");
		admin.setName("admin");
		admin.setUserRole(UserRole.ADMIN);
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userRepository.save(admin);
	}
}


	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		Optional<User> optionalUser=userRepository.findFirstByEmail(studentDto.getEmail());
		if(optionalUser.isEmpty()) {
			User user=new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setUserRole(UserRole.STUDENT);
			User createdUser=userRepository.save(user);
			StudentDto createdStudentDto=new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
		}
		return null;
	}


	@Override
	public List<StudentDto> getAllStudents() {
		
		return userRepository.getAllStudentByUserRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}


	@Override
	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(studentId);
	}


	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser= userRepository.findById(studentId);
		
			SingleStudentDto singleStudentDto=new SingleStudentDto();
			 optionalUser.ifPresent(user->singleStudentDto.setStudentDto(user.getStudentDto()));
		     return singleStudentDto;
	}


	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentId);
		if(optionalUser.isPresent()) {
			
			  User user=optionalUser.get(); user.setName(studentDto.getName());
			  user.setAddress(studentDto.getAddress()); user.setDob(studentDto.getDob());
			  user.setEmail(studentDto.getEmail());
			  user.setFatherName(studentDto.getFatherName());
			  user.setMotherName(studentDto.getMotherName());
			  user.setGender(studentDto.getGender());
			  user.setStudentClass(studentDto.getStudentClass()); User
			  updateStudent=userRepository.save(user); StudentDto updateStudentDto=new
			  StudentDto(); updateStudentDto.setId(updateStudent.getId()); 
			  return updateStudentDto;
			 
			
			/*
			 * User user=optionalUser.get(); BeanUtils.copyProperties(studentDto, user);
			 * User updateStudent=userRepository.save(user); StudentDto updateStudentDto=new
			 * StudentDto(); updateStudentDto.setId(updateStudent.getId()); return
			 * updateStudentDto;
			 */
			 
			
		}
		return null;
	}


	@Override	
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalStudent=userRepository.findById(studentId);
		if(optionalStudent.isPresent()) {
			Fee fee=new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreatedDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee=feeRepository.save(fee);
			FeeDto paidFeeDto=new FeeDto();
			paidFee.setId(paidFee.getId());
			return paidFeeDto;
			
		}
		return null;
	}


	@Override
	public List<StudentLeaveDto> getAllAppliedLeaves() {
		// TODO Auto-generated method stub
		return studentLeaveRepository.findAll().stream()
				.map(StudentLeave:: getStudentLeaveDto).collect(Collectors.toList());
		
	}


	@Override
	public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
		Optional<StudentLeave> optionalStudentLeave	=studentLeaveRepository.findById(leaveId);
		if(optionalStudentLeave.isPresent()) {
			StudentLeave studentLeave=optionalStudentLeave.get();
			if(java.util.Objects.equals(status, "Approved")) {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);;
				
			}else {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
			}
			StudentLeave updatedStudentLeave=studentLeaveRepository.save(studentLeave);
			StudentLeaveDto updatedStudentLeaveDto=new StudentLeaveDto();
			updatedStudentLeaveDto.setId(updatedStudentLeave.getId());
			return updatedStudentLeaveDto;
		}
		return null;
	}


	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
		Teacher teacher=new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher=teacherRepository.save(teacher);
		TeacherDto createdTeacherDto=new TeacherDto();
		createdTeacherDto.setId(createdTeacher.getId());
		return createdTeacherDto;
	}


	@Override
	public List<TeacherDto> getAllTeachers() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}


	@Override
	public void deleteTeacher(Long teacherId) {
		
		teacherRepository.deleteById(teacherId);
		
	}


	@Override
	public SingleTeacherDto getTeacherById(Long teacherId) {
		Optional<Teacher> optionalTeacher=teacherRepository.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			SingleTeacherDto singleTeacherDto=new SingleTeacherDto();
			singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
			return singleTeacherDto;
 		}
		return null;
	}


	@Override
	public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
		Optional<Teacher> optionalTeacher=teacherRepository.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			Teacher updateTeachers=optionalTeacher.get();
			updateTeachers.setName(teacherDto.getName());
			updateTeachers.setGender(teacherDto.getGender());
			updateTeachers.setAddress(teacherDto.getAddress());
			updateTeachers.setDepartment(teacherDto.getDepartment());
			updateTeachers.setDob(teacherDto.getDob());
			updateTeachers.setQualification(teacherDto.getQualification());
			Teacher updatedTeacher=teacherRepository.save(updateTeachers);
			TeacherDto updatedTeacherDto=new TeacherDto();
			updatedTeacherDto.setId(updatedTeacher.getId());
			return updatedTeacherDto;
		}
		return null;
	}
}
