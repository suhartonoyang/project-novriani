package com.project.novriani.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.novriani.bean.Response;

@RestController
@RequestMapping("/api/k-means")
@CrossOrigin
public class KMeansController {
	
	@RequestMapping("/helloWorld")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/run")
	public ResponseEntity<Response> runKMeans(){
		Response resp = new Response();
		
		resp.setCode(String.valueOf(HttpStatus.OK.value()));
		resp.setStatus(HttpStatus.OK.name());
		return ResponseEntity.ok(resp);
	}
}
