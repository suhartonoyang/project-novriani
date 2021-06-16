package com.project.novriani.bean;

public class StudentDTO {

	private String studentName;
	private String classroomName;
	private int agama;
	private int pkn;
	private int bindo;
	private int mtk;
	private int sbpd;
	private int pjs;

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(String studentName, String classroomName, int agama, int pkn, int bindo, int mtk, int sbpd,
			int pjs) {
		super();
		this.studentName = studentName;
		this.classroomName = classroomName;
		this.agama = agama;
		this.pkn = pkn;
		this.bindo = bindo;
		this.mtk = mtk;
		this.sbpd = sbpd;
		this.pjs = pjs;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public int getAgama() {
		return agama;
	}

	public void setAgama(int agama) {
		this.agama = agama;
	}

	public int getPkn() {
		return pkn;
	}

	public void setPkn(int pkn) {
		this.pkn = pkn;
	}

	public int getBindo() {
		return bindo;
	}

	public void setBindo(int bindo) {
		this.bindo = bindo;
	}

	public int getMtk() {
		return mtk;
	}

	public void setMtk(int mtk) {
		this.mtk = mtk;
	}

	public int getSbpd() {
		return sbpd;
	}

	public void setSbpd(int sbpd) {
		this.sbpd = sbpd;
	}

	public int getPjs() {
		return pjs;
	}

	public void setPjs(int pjs) {
		this.pjs = pjs;
	}

	@Override
	public String toString() {
		return "StudentDTO [studentName=" + studentName + ", classroomName=" + classroomName + ", agama=" + agama
				+ ", pkn=" + pkn + ", bindo=" + bindo + ", mtk=" + mtk + ", sbpd=" + sbpd + ", pjs=" + pjs + "]";
	}

}
