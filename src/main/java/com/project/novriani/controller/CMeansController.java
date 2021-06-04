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
import com.project.novriani.bean.Matrix;
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
import com.project.novriani.service.cmeans.CMeansService2;
import com.project.novriani.service.kmeans.EuclideanDistance;
import com.project.novriani.service.kmeans.KMeansService;

@RestController
@RequestMapping("/api/c-means")
@CrossOrigin
public class CMeansController {

	@Autowired
	private CMeansService1 cMeansService1;

	@Autowired
	private CMeansService2 cMeansService2;

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/run")
	public ResponseEntity<Response> run(@RequestParam int clusterNumber, @RequestParam int maxIteration,
			@RequestParam int fuzziness, @RequestParam double epsilon) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<Matrix> result = cMeansService1.run(clusterNumber, maxIteration, fuzziness, epsilon);
		resp.setData(result);
		
//		double[][] run2 = cMeansService2.run(clusterNumber, maxIteration, fuzziness, epsilon);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/run2")
	public ResponseEntity<Response> run2(@RequestParam int clusterNumber, @RequestParam int maxIteration,
			@RequestParam int fuzziness, @RequestParam double epsilon) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		
		
		return ResponseEntity.ok(resp);
	}
}
