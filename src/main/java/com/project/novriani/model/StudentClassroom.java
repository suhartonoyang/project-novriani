// Generated with g9.

package com.project.novriani.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "student_classroom")
public class StudentClassroom implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 19)
	private long id;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "updated_by", length = 255)
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", nullable = false)
	@JsonIgnoreProperties("studentClassrooms")
	private Student student;
	@ManyToOne(optional = false)
	@JoinColumn(name = "classroom_id", nullable = false)
	@JsonIgnoreProperties("studentClassrooms")
	private Classroom classroom;
	@OneToMany(mappedBy = "studentClassroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "studentClassroom" })
	private List<Enroll> enrolls;

	/** Default constructor. */
	public StudentClassroom() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Listter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(long aId) {
		id = aId;
	}

	/**
	 * Access method for createdBy.
	 *
	 * @return the current value of createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Listter method for createdBy.
	 *
	 * @param aCreatedBy the new value for createdBy
	 */
	public void setCreatedBy(String aCreatedBy) {
		createdBy = aCreatedBy;
	}

	/**
	 * Access method for createdDate.
	 *
	 * @return the current value of createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Listter method for createdDate.
	 *
	 * @param aCreatedDate the new value for createdDate
	 */
	public void setCreatedDate(Date aCreatedDate) {
		createdDate = aCreatedDate;
	}

	/**
	 * Access method for updatedBy.
	 *
	 * @return the current value of updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Listter method for updatedBy.
	 *
	 * @param aUpdatedBy the new value for updatedBy
	 */
	public void setUpdatedBy(String aUpdatedBy) {
		updatedBy = aUpdatedBy;
	}

	/**
	 * Access method for updatedDate.
	 *
	 * @return the current value of updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Listter method for updatedDate.
	 *
	 * @param aUpdatedDate the new value for updatedDate
	 */
	public void setUpdatedDate(Date aUpdatedDate) {
		updatedDate = aUpdatedDate;
	}

	/**
	 * Access method for student.
	 *
	 * @return the current value of student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Listter method for student.
	 *
	 * @param aStudent the new value for student
	 */
	public void setStudent(Student aStudent) {
		student = aStudent;
	}

	/**
	 * Access method for classroom.
	 *
	 * @return the current value of classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * Listter method for classroom.
	 *
	 * @param aClassroom the new value for classroom
	 */
	public void setClassroom(Classroom aClassroom) {
		classroom = aClassroom;
	}

	public List<Enroll> getEnrolls() {
		return enrolls;
	}

	public void setEnrolls(List<Enroll> enrolls) {
		this.enrolls = enrolls;
	}

	/**
	 * Compares the key for this instance with another StudentClassroom.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class StudentClassroom and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentClassroom)) {
			return false;
		}
		StudentClassroom that = (StudentClassroom) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another StudentClassroom.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof StudentClassroom))
			return false;
		return this.equalKeys(other) && ((StudentClassroom) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = (int) (getId() ^ (getId() >>> 32));
		result = 37 * result + i;
		return result;
	}

	@Override
	public String toString() {
		return "StudentClassroom [id=" + id + ", enrolls=" + enrolls + "]";
	}

}
