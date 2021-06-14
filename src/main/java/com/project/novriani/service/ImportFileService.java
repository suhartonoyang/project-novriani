package com.project.novriani.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Sets;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;
import com.project.novriani.model.Classroom;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;

@Service
public class ImportFileService {

	@Autowired
	private LessonService lessonService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentClassroomService studentClassroomService;

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private EnrollService enrollService;

	public Map<Integer, String> headerMap = ImmutableMap.<Integer, String>builder().put(2, "Agama").put(3, "PKN")
			.put(4, "B Indo").put(5, "MTK").put(6, "SBDP").put(7, "PJS").build();

	@Transactional
	public List<Student> importFile(InputStream input, String username) throws IOException, FileNotFoundException {
		Workbook workbook = new XSSFWorkbook(input);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		List<Student> students = new ArrayList<Student>();
		while (rows.hasNext()) {
			Row currentRow = rows.next();
			Iterator<Cell> cells = currentRow.iterator();
			Student student = new Student();
			HashSet<StudentClassroom> studentClassrooms = new HashSet<>();
			StudentClassroom studentClassroom = new StudentClassroom();
			List<Enroll> enrolls = new ArrayList<Enroll>();

			int cellIdx = 0;
			while (cells.hasNext()) {
				Cell currentCell = cells.next();
				Lesson lesson = null;
				Enroll enroll = new Enroll();
				if (cellIdx == 0) {
					student.setStudentName(currentCell.getStringCellValue());
					student.setCreatedBy(username);
					student.setCreatedDate(new Date());
					student = studentService.save(student);
				} else if (cellIdx == 1) {
					Classroom classroom = classroomService
							.getClassroomByClassroomName(currentCell.getStringCellValue());
					
					if (classroom == null) {
						classroom = new Classroom();
						classroom.setClassroomName(currentCell.getStringCellValue());
						classroom.setCreatedBy(username);
						classroom.setCreatedDate(new Date());
						classroom = classroomService.save(classroom);
					}
					
					studentClassroom.setCreatedBy(username);
					studentClassroom.setCreatedDate(new Date());
					studentClassroom.setStudent(student);
					studentClassroom.setClassroom(classroom);
					studentClassroom = studentClassroomService.save(studentClassroom);
				} else {
					lesson = lessonService.getLessonByLessonName(headerMap.get(cellIdx));
					enroll.setLesson(lesson);
					enroll.setScore((int) currentCell.getNumericCellValue());
					enroll.setstudentClassroom(studentClassroom);
					enroll = enrollService.save(enroll);
					enrolls.add(enroll);
				}

				cellIdx++;
			}
			studentClassroom.setEnrolls(enrolls);
			studentClassrooms.add(studentClassroom);
			student.setStudentClassrooms(studentClassrooms);
			students.add(student);
		}

		workbook.close();
		return students;
	}
}
