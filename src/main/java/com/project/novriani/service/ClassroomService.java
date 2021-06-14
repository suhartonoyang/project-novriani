package com.project.novriani.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Classroom;
import com.project.novriani.model.Enroll;
import com.project.novriani.repo.ClassroomRepository;
import com.project.novriani.repo.EnrollRepository;

@Service
public class ClassroomService {


	@Autowired
	private ClassroomRepository classroomRepository;
	
	public List<Classroom> getClassroomAll(){
		return StreamSupport.stream(classroomRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public Classroom getClassroomByClassroomName(String classroomName) {
		return Optional.ofNullable(classroomRepository.findClassroomByClassroomName(classroomName)).orElse(null);
	}
	
	public Classroom save(Classroom classroom) {
		return classroomRepository.save(classroom);
	}
}
