package com.project.novriani.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.novriani.bean.Centroid;
import com.project.novriani.bean.Cluster;
import com.project.novriani.bean.Record;
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
import com.project.novriani.service.EuclideanDistance;
import com.project.novriani.service.KMeansService;

@RestController
@RequestMapping("/api/k-means")
@CrossOrigin
public class KMeansController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired
	private StudentClassroomRepository studentClassroomRepository;

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private EnrollRepository enrollRepository;

	@Autowired
	private KMeansService kMeansService;

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/run")
	public ResponseEntity<Response> run(@RequestParam int totalClusters, @RequestParam int maxIterations) {
		Response resp = new Response();
		List<Cluster> clusters = kMeansService.run(totalClusters, new EuclideanDistance(), maxIterations);

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		clusters.stream().forEach(p -> {
			System.out.println("------------------------------ CLUSTER " + p.getClusterNumber()
					+ "-----------------------------------");
			System.out.println(kMeansService.sortedCentroid(p.getCentroid()));
			List<String> memberList = p.getRecords().stream().map(n -> n.getStudentName()).collect(Collectors.toList());
			memberList.stream().sorted();
			System.out.print(memberList.toString());
			System.out.println();
			System.out.println();
		});
		resp.setData(clusters);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/student")
	public ResponseEntity<Response> getSudentAll() {
		Response resp = new Response();
		List<Student> students = new ArrayList<>();
		Iterable<Student> studentItr = studentRepository.findAll();
		for (Student student : studentItr) {
			System.out.println(student.toString());
			students.add(student);
		}

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		resp.setData(students);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/classroom")
	public ResponseEntity<Response> getClassroomAll() {
		Response resp = new Response();
		List<Classroom> classrooms = new ArrayList<>();
		Iterable<Classroom> classroomItr = classroomRepository.findAll();
		for (Classroom classroom : classroomItr) {
			System.out.println(classroom.toString());
			classrooms.add(classroom);
		}

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		resp.setData(classrooms);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/student-classroom")
	public ResponseEntity<Response> getStudentClassroomAll() {
		Response resp = new Response();
		List<StudentClassroom> studentClassrooms = new ArrayList<>();
		Iterable<StudentClassroom> studentClassroomItr = studentClassroomRepository.findAll();
		for (StudentClassroom studentClassroom : studentClassroomItr) {
			System.out.println(studentClassroom.toString());
			studentClassrooms.add(studentClassroom);
		}

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		resp.setData(studentClassrooms);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/lesson")
	public ResponseEntity<Response> getLessonAll() {
		Response resp = new Response();
		List<Lesson> lessons = new ArrayList<>();
		Iterable<Lesson> lessonItr = lessonRepository.findAll();
		for (Lesson lesson : lessonItr) {
			System.out.println(lesson.toString());
			lessons.add(lesson);
		}

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		resp.setData(lessons);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/enroll")
	public ResponseEntity<Response> getEnrollAll() {
		Response resp = new Response();
		List<Enroll> enrolls = new ArrayList<>();
		Iterable<Enroll> enrollItr = enrollRepository.findAll();
		for (Enroll enroll : enrollItr) {
			System.out.println(enroll.toString());
			enrolls.add(enroll);
		}

		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		resp.setData(enrolls);
		return ResponseEntity.ok(resp);
	}

}
