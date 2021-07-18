package com.project.novriani.bean;

import java.util.Map;
import java.util.Objects;

/**
 * Encapsulates all feature values for a few attributes. Optionally each record
 * can be described with the {@link #description} field.
 */
public class Record {

	/**
	 * The record description. For example, this can be the artist name for the
	 * famous musician example.
	 */
	private final long studentId;
	private final String studentName;
	private final int centroidNumber;

	/**
	 * Encapsulates all attributes and their corresponding values, i.e. features.
	 */
	private final Map<String, Double> features;

	public Record(long studentId, String studentName, Map<String, Double> features, int centroidNumber) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.features = features;
		this.centroidNumber = centroidNumber;
	}

	public long getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public int getCentroidNumber() {
		return centroidNumber;
	}

	public Map<String, Double> getFeatures() {
		return features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (studentId != other.studentId)
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Record [studentId=" + studentId + ", studentName=" + studentName + ", centroidNumber=" + centroidNumber
				+ ", features=" + features + "]";
	}

}