package com.project.novriani.bean;

public class StudentClusterDTO {

	private long studentId;
	private String studentName;
	private int clusterNumber;

	public StudentClusterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentClusterDTO(long studentId, String studentName, int clusterNumber) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.clusterNumber = clusterNumber;
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

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	@Override
	public String toString() {
		return "StudentClusterDTO [studentId=" + studentId + ", studentName=" + studentName + ", clusterNumber="
				+ clusterNumber + "]";
	}

}
