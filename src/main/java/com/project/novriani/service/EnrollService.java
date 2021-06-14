package com.project.novriani.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.repo.EnrollRepository;

@Service
public class EnrollService {


	@Autowired
	private EnrollRepository enrollRepository;
	
	public List<Enroll> getEnrollsAll(){
		return StreamSupport.stream(enrollRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public Enroll save(Enroll enroll) {
		return enrollRepository.save(enroll);
	}
}
