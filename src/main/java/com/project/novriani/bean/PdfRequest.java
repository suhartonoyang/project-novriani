package com.project.novriani.bean;

public class PdfRequest {
	private int no;
	private String nama;
	private String hasil;

	public PdfRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PdfRequest(int no, String nama, String hasil) {
		super();
		this.no = no;
		this.nama = nama;
		this.hasil = hasil;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getHasil() {
		return hasil;
	}

	public void setHasil(String hasil) {
		this.hasil = hasil;
	}

	@Override
	public String toString() {
		return "PdfRequest [no=" + no + ", nama=" + nama + ", hasil=" + hasil + "]";
	}

}
