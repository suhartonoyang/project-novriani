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

@Entity(name = "lesson")
public class Lesson implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 19)
	private long id;
	@Column(name = "lesson_name", nullable = false, length = 255)
	private String lessonName;
	@Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	@Column(name = "updated_by", length = 255)
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@OneToMany(mappedBy = "lesson")
	@JsonIgnoreProperties("lesson")
	private Set<Enroll> enroll;

	/** Default constructor. */
	public Lesson() {
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
	 * Access method for lessonName.
	 *
	 * @return the current value of lessonName
	 */
	public String getLessonName() {
		return lessonName;
	}

	/**
	 * Setter method for lessonName.
	 *
	 * @param aLessonName the new value for lessonName
	 */
	public void setLessonName(String aLessonName) {
		lessonName = aLessonName;
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

	/**
	 * Access method for enroll.
	 *
	 * @return the current value of enroll
	 */
	public Set<Enroll> getEnroll() {
		return enroll;
	}

	/**
	 * Setter method for enroll.
	 *
	 * @param aEnroll the new value for enroll
	 */
	public void setEnroll(Set<Enroll> aEnroll) {
		enroll = aEnroll;
	}

	/**
	 * Compares the key for this instance with another Lesson.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Lesson and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Lesson)) {
			return false;
		}
		Lesson that = (Lesson) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Lesson.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Lesson))
			return false;
		return this.equalKeys(other) && ((Lesson) other).equalKeys(this);
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
		StringBuffer sb = new StringBuffer("[Lesson |");
		sb.append(" id=").append(getId());
		sb.append("]");
		return sb.toString();
	}

}
