package com.project.novriani.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.repo.EnrollRepository;
import com.project.novriani.repo.StudentClassroomRepository;

@Service
public class StudentClassroomService {

	@Autowired
	private StudentClassroomRepository studentClassroomRepository;

	public List<StudentClassroom> getStudentClassroomAll() {
		return StreamSupport.stream(studentClassroomRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<StudentClassroom> saveAll(List<StudentClassroom> studentClassrooms) {
		return StreamSupport.stream(studentClassroomRepository.saveAll(studentClassrooms).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public StudentClassroom save(StudentClassroom studentClassroom) {
		return studentClassroomRepository.save(studentClassroom);
	}

}
