package com.onlinetestchallenge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Score {

	@Id
    @Column(name="id")
    @GeneratedValue
    private long id;
	
	private int grade;
	
	private int totalExam;
	
	private boolean flgIsAproved;
	
	private String nameExam;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "score_user")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getTotalExam() {
		return totalExam;
	}

	public void setTotalExam(int totalExam) {
		this.totalExam = totalExam;
	}

	public boolean isFlgIsAproved() {
		return flgIsAproved;
	}

	public void setFlgIsAproved(boolean flgIsAproved) {
		this.flgIsAproved = flgIsAproved;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNameExam() {
		return nameExam;
	}

	public void setNameExam(String nameExam) {
		this.nameExam = nameExam;
	}
	
}
