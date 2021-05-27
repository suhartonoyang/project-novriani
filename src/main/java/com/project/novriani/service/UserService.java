package com.project.novriani.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.novriani.model.User;
import com.project.novriani.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User registerUser(User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		newUser.setCreated_date(new Date());
		return userRepository.save(newUser);
	}
	
	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user==null) 
			return null;

		Boolean isPasswordMatches = bCryptPasswordEncoder.matches(password, user.getPassword());
		if (!isPasswordMatches)
			return null;
		
		return user;
	}

}
