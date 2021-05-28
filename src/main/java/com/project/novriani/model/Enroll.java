// Generated with g9.

package com.project.novriani.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "enroll")
public class Enroll implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 19)
	private long id;
	@Column(nullable = false, precision = 10)
	private int score;
	@ManyToOne(optional = false)
	@JoinColumn(name = "lesson_id", nullable = false)
	@JsonIgnoreProperties("enroll")
	private Lesson lesson;
	@ManyToOne
	@JoinColumn(name = "student_classroom_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("enrolls")
	private StudentClassroom studentClassroom;

	/** Default constructor. */
	public Enroll() {
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
	 * Access method for score.
	 *
	 * @return the current value of score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter method for score.
	 *
	 * @param aScore the new value for score
	 */
	public void setScore(int aScore) {
		score = aScore;
	}

	/**
	 * Access method for lesson.
	 *
	 * @return the current value of lesson
	 */
	public Lesson getLesson() {
		return lesson;
	}

	/**
	 * Setter method for lesson.
	 *
	 * @param aLesson the new value for lesson
	 */
	public void setLesson(Lesson aLesson) {
		lesson = aLesson;
	}

	public StudentClassroom getstudentClassroom() {
		return studentClassroom;
	}

	public void setstudentClassroom(StudentClassroom studentClassroom) {
		this.studentClassroom = studentClassroom;
	}

	/**
	 * Compares the key for this instance with another Enroll.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Enroll and the key objects
	 *         are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Enroll)) {
			return false;
		}
		Enroll that = (Enroll) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Enroll.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Enroll))
			return false;
		return this.equalKeys(other) && ((Enroll) other).equalKeys(this);
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
		StringBuffer sb = new StringBuffer("[Enroll |");
		sb.append(" id=").append(getId());
		sb.append("]");
		return sb.toString();
	}

}
