package com.project.novriani.bean;

import java.util.List;

public class Matrix {
	private long studentId;
	private String studentName;
	private int row;
	private List<Matrix2D> columns;

	public Matrix() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matrix(long studentId, String studentName, int row, List<Matrix2D> columns) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.row = row;
		this.columns = columns;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public List<Matrix2D> getColumns() {
		return columns;
	}

	public void setColumns(List<Matrix2D> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Matrix [studentId=" + studentId + ", studentName=" + studentName + ", row=" + row + ", columns="
				+ columns + "]";
	}

}
