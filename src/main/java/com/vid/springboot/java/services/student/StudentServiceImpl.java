package com.vid.springboot.java.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vid.springboot.java.dto.SingleStudentDto;
import com.vid.springboot.java.dto.StudentDto;
import com.vid.springboot.java.dto.StudentLeaveDto;
import com.vid.springboot.java.entities.StudentLeave;
import com.vid.springboot.java.entities.User;
import com.vid.springboot.java.repository.StudentLeaveRepository;
import com.vid.springboot.java.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final UserRepository userRepository;
	
	private final StudentLeaveRepository studentLeaveRepository;

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser= userRepository.findById(studentId);
		
		SingleStudentDto singleStudentDto=new SingleStudentDto();
		 optionalUser.ifPresent(user->singleStudentDto.setStudentDto(user.getStudentDto()));
	     return singleStudentDto;
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(studentLeaveDto.getUserId());
		if(optionalUser.isPresent()) {
			StudentLeave studentLeave=new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setDate(new Date());
			studentLeave.setStudentLeaveStatus(studentLeaveDto.getStudentLeaveStatus().Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave submittedStudentLeave=studentLeaveRepository.save(studentLeave);
			StudentLeaveDto applyStudentLeave=new StudentLeaveDto();
			applyStudentLeave.setId(submittedStudentLeave.getId());
			return applyStudentLeave;
			
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return studentLeaveRepository.findAllByUserId(studentId).stream()
				.map(StudentLeave:: getStudentLeaveDto).collect(Collectors.toList());
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
	}
