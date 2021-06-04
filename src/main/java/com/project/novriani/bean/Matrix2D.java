package com.project.novriani.bean;

public class Matrix2D {
	private int column;
	private int clusterNumber;
	private double value;

	public Matrix2D() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matrix2D(int column, int clusterNumber, double value) {
		super();
		this.column = column;
		this.clusterNumber = clusterNumber;
		this.value = value;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getClusterNumber() {
		return clusterNumber;
	}

	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Matrix2D [column=" + column + ", clusterNumber=" + clusterNumber + ", value=" + value + "]";
	}

}
