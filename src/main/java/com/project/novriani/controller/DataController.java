package com.project.novriani.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.novriani.bean.PdfRequest;
import com.project.novriani.bean.PdfRequestList;
import com.project.novriani.bean.Response;
import com.project.novriani.bean.ResponseImport;
import com.project.novriani.bean.StudentDTO;
import com.project.novriani.model.Student;
import com.project.novriani.service.ExportFileService;
import com.project.novriani.service.ImportFileService;
import com.project.novriani.service.StudentService;

@RestController
@RequestMapping("/api/data")
@CrossOrigin
public class DataController {

	@Autowired
	private ImportFileService importFileService;

	@Autowired
	private ExportFileService exportFileService;

	@Autowired
	private StudentService studentService;

	@Value("${file.tempDir}")
	private String BASE_DIR;

	@GetMapping("/students")
	public ResponseEntity<Response> getSudentAll() {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());

		List<Student> students = studentService.getStudentAll();
		List<StudentDTO> dtos = studentService.convertToDto(students);
		resp.setData(dtos);

		return ResponseEntity.ok(resp);
	}

	@PostMapping("/import")
	public ResponseEntity<Response> importFile(@RequestParam MultipartFile file, @RequestParam String username) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());
		List<Student> students = null;
		try {
			ResponseImport respImport = importFileService.importFile(file.getInputStream(), username);
			if (respImport.getMessage() != null) {
				resp.setCode("500");
				resp.setMessage(respImport.getMessage());
				return ResponseEntity.ok(resp);
			}
			students = respImport.getStudents();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		resp.setData(students);
		return ResponseEntity.ok(resp);
	}
//
//	@PostMapping("/download")
//	public ResponseEntity<Resource> downloadFile(@RequestBody PdfRequestList data) {
//		Response resp = new Response();
//		resp.setCode(String.valueOf(HttpStatus.OK.value()));
//		resp.setMessage(HttpStatus.OK.name());
//
//		List<Student> students = null;
//		String path = exportFileService.writeToPDF(BASE_DIR, data);
//		resp.setData(students);
//
//		Path file = Paths.get(path);
//
//		if (!file.startsWith(BASE_DIR))
//			file = Paths.get(BASE_DIR).resolve(file);
//
//		return doOpenFile(file, null, null);
//	}

	@PostMapping("/download")
	public ResponseEntity<Response> downloadFile(@RequestBody PdfRequestList data) {
		Response resp = new Response();
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setMessage(HttpStatus.OK.name());

		List<Student> students = null;
		String path = exportFileService.writeToPDF(BASE_DIR, data);
		Path file = Paths.get(path);

		resp.setData(Arrays.asList(file));

		return ResponseEntity.ok().body(resp);
	}

	private ResponseEntity<Resource> doOpenFile(Path file, String filename, String mimeType) {
		if (file == null || !Files.exists(file) || !Files.isRegularFile(file))
			return ResponseEntity.notFound().build();

		if (filename == null)
			filename = file.getFileName().toString();

		HttpHeaders headers = new HttpHeaders();
		if (mimeType != null)
			headers.setContentType(MediaType.parseMediaType(mimeType));
		else
			MediaTypeFactory.getMediaType(filename).ifPresent(headers::setContentType);

		headers.setContentDisposition(ContentDisposition.builder("inline").filename(filename).build());

		return ResponseEntity.ok().headers(headers).body(new FileSystemResource(file));
	}

}
