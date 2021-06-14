package com.project.novriani.controller;

import java.util.Arrays;
import java.util.List;

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

import com.project.novriani.bean.Response;
import com.project.novriani.model.User;
import com.project.novriani.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user) {
		User newUser = userService.registerUser(user);
		Response resp = new Response();

		if (newUser != null) {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage("Sucessfully Register!");
			resp.setData(Arrays.asList(newUser));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@GetMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String username, @RequestParam String password) {
		User user = userService.loginUser(username, password);
		Response resp = new Response();
		if (user == null) {
			resp.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
			resp.setMessage("Incorrect username or password!");
			resp.setData(null);
		} else {
			resp.setCode(String.valueOf(HttpStatus.OK.value()));
			resp.setMessage("Sucessfully Login!");
			resp.setData(Arrays.asList(user));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
}
