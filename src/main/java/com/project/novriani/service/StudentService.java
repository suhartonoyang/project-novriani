package com.project.novriani.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.model.Student;
import com.project.novriani.repo.EnrollRepository;
import com.project.novriani.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getStudentAll() {
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<Student> saveAll(List<Student> students) {
		return StreamSupport.stream(studentRepository.saveAll(students).spliterator(), false)
				.collect(Collectors.toList());
	}
	
	public Student save(Student student) {
		return studentRepository.save(student);
	}

}
