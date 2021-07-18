package com.project.novriani.bean;

import java.util.List;

import com.project.novriani.model.Student;

public class ResponseImport {
	private String message;
	private List<Student> students;

	public ResponseImport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseImport(String message, List<Student> students) {
		super();
		this.message = message;
		this.students = students;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ResponseImport [message=" + message + ", students=" + students + "]";
	}

}
