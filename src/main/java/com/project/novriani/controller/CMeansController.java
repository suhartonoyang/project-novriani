package com.project.novriani.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.novriani.bean.Cluster;
import com.project.novriani.bean.Response;
import com.project.novriani.model.Classroom;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.repo.ClassroomRepository;
import com.project.novriani.repo.EnrollRepository;
import com.project.novriani.repo.LessonRepository;
import com.project.novriani.repo.StudentClassroomRepository;
import com.project.novriani.repo.StudentRepository;
import com.project.novriani.service.cmeans.CMeansService;
import com.project.novriani.service.cmeans.CMeansService1;
import com.project.novriani.service.kmeans.EuclideanDistance;
import com.project.novriani.service.kmeans.KMeansService;

@RestController
@RequestMapping("/api/c-means")
@CrossOrigin
public class CMeansController {

	@Autowired
	private CMeansService cMeansService;
	
	@Autowired
	private CMeansService1 cMeansService1;
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/run")
	public ResponseEntity<Response> run() {
		Response resp = new Response();
//		int clusterNumber = 5;
//		int maxIteration = 100;
//		int fuzziness = 2;
//		double epsilon = 0.01;
//
//		cMeansService.run(clusterNumber, maxIteration, fuzziness, epsilon);
		
		cMeansService1.run();
		return ResponseEntity.ok(resp);
	}
}
