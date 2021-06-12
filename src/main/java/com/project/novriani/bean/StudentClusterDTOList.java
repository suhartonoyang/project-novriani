package com.project.novriani.bean;

import java.util.List;

public class StudentClusterDTOList {

	private int totalCluster;
	private List<StudentClusterDTO> students;

	public StudentClusterDTOList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentClusterDTOList(int totalCluster, List<StudentClusterDTO> students) {
		super();
		this.totalCluster = totalCluster;
		this.students = students;
	}

	public int getTotalCluster() {
		return totalCluster;
	}

	public void setTotalCluster(int totalCluster) {
		this.totalCluster = totalCluster;
	}

	public List<StudentClusterDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentClusterDTO> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "StudentClusterDTOList [totalCluster=" + totalCluster + ", students=" + students + "]";
	}

}
