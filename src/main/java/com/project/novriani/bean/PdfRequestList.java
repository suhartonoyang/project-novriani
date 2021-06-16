package com.project.novriani.bean;

import java.util.List;

public class PdfRequestList {
	private String username;
	private List<PdfRequest> data;

	public PdfRequestList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PdfRequestList(String username, List<PdfRequest> data) {
		super();
		this.username = username;
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PdfRequest> getData() {
		return data;
	}

	public void setData(List<PdfRequest> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PdfRequestList [username=" + username + ", data=" + data + "]";
	}

}
