package com.project.novriani.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.repo.EnrollRepository;
import com.project.novriani.repo.LessonRepository;
import com.project.novriani.repo.StudentRepository;

@Service
public class LessonService {


	@Autowired
	private LessonRepository lessonRepository;
	
	public List<Lesson> getLessonAll(){
		return StreamSupport.stream(lessonRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public Lesson getLessonByLessonName(String lessonName) {
		return lessonRepository.findLessonByLessonName(lessonName);
	}
}
