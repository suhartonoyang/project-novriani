// Generated with g9.

package com.project.novriani.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "classroom")
public class Classroom implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 19)
	private long id;
	@Column(name = "classroom_name", nullable = false, length = 255)
	private String classroomName;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "updated_by", length = 255)
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
//	@ManyToMany
//	@JoinTable(
//			name = "student_classroom",
//			joinColumns = @JoinColumn(name = "classroom_id"),
//			inverseJoinColumns = @JoinColumn(name = "student_id")
//	)
	@OneToMany(mappedBy = "classroom")
	@JsonIgnoreProperties(value = { "classroom" })
	private Set<StudentClassroom> studentClassrooms;

	/** Default constructor. */
	public Classroom() {
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
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(long aId) {
		id = aId;
	}

	/**
	 * Access method for classroomName.
	 *
	 * @return the current value of classroomName
	 */
	public String getClassroomName() {
		return classroomName;
	}

	/**
	 * Setter method for classroomName.
	 *
	 * @param aClassroomName the new value for classroomName
	 */
	public void setClassroomName(String aClassroomName) {
		classroomName = aClassroomName;
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
	 * Setter method for createdBy.
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
	 * Setter method for createdDate.
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
	 * Setter method for updatedBy.
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
	 * Setter method for updatedDate.
	 *
	 * @param aUpdatedDate the new value for updatedDate
	 */
	public void setUpdatedDate(Date aUpdatedDate) {
		updatedDate = aUpdatedDate;
	}

	public Set<StudentClassroom> getStudentClassrooms() {
		return studentClassrooms;
	}

	public void setStudentClassrooms(Set<StudentClassroom> studentClassrooms) {
		this.studentClassrooms = studentClassrooms;
	}

	/**
	 * Compares the key for this instance with another Classroom.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Classroom and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Classroom)) {
			return false;
		}
		Classroom that = (Classroom) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Classroom.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Classroom))
			return false;
		return this.equalKeys(other) && ((Classroom) other).equalKeys(this);
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

	/**
	 * Returns a debug-friendly String representation of this instance.
	 *
	 * @return String representation of this instance
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[Classroom |");
		sb.append(" id=").append(getId());
		sb.append("]");
		return sb.toString();
	}

}
