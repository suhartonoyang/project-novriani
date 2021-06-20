package com.project.novriani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.bean.StudentDTO;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Student;
import com.project.novriani.repo.ClassroomRepository;
import com.project.novriani.repo.EnrollRepository;
import com.project.novriani.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private ClassroomRepository classroomRepository;

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
	
	@Transactional
	public String deleteAll() {
		classroomRepository.deleteAll();
		studentRepository.deleteAll();		
		return "OK";
	}

	public List<StudentDTO> convertToDto(List<Student> students) {
		List<StudentDTO> dtos = new ArrayList<StudentDTO>();

		students.stream().forEach(p -> {
			StudentDTO dto = new StudentDTO();
			dto.setStudentName(p.getStudentName());
			p.getStudentClassrooms().forEach(q -> {
				dto.setClassroomName(q.getClassroom().getClassroomName());
				q.getEnrolls().stream().forEach(r -> {
					switch (r.getLesson().getLessonName().toLowerCase()) {
					case "agama":
						dto.setAgama(r.getScore());
						break;
					case "pkn":
						dto.setPkn(r.getScore());
						break;
					case "b indo":
						dto.setBindo(r.getScore());
						break;
					case "mtk":
						dto.setMtk(r.getScore());
						break;
					case "sbdp":
						dto.setSbpd(r.getScore());
						break;
					case "pjs":
						dto.setPjs(r.getScore());
						break;
					default:
						break;
					}
				});
			});
			dtos.add(dto);
		});

		return dtos;
	}

}
