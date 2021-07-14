package com.project.novriani.bean;

import java.util.List;

public class StudentClusterDTOList {

	private int totalCluster;
	private List<StudentClusterDTO> students;
	private List<Cluster> clusters;

	public StudentClusterDTOList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentClusterDTOList(int totalCluster, List<StudentClusterDTO> students, List<Cluster> clusters) {
		super();
		this.totalCluster = totalCluster;
		this.students = students;
		this.clusters = clusters;
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

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	@Override
	public String toString() {
		return "StudentClusterDTOList [totalCluster=" + totalCluster + ", students=" + students + ", clusters=" + clusters + "]";
	}

}
