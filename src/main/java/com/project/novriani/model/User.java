package com.project.novriani.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "password")
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name = "created_date")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date createdDate;
	@Column(name = "updated_date")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreated_date() {
		return createdDate;
	}

	public void setCreated_date(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdated_date() {
		return updatedDate;
	}

	public void setUpdated_date(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
