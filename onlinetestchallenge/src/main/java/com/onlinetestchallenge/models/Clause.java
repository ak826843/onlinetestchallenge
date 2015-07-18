package com.onlinetestchallenge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Clause {

	@Id
    @Column(name="id")
    @GeneratedValue
    private long id;
	
	private String description;
	
	private boolean flagIsCorrect;
	
	@Column(name = "`order`") 
	private short order;
	
	@Transient
	public boolean isChecked;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clause_question")
	private Question question;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFlagIsCorrect() {
		return flagIsCorrect;
	}

	public void setFlagIsCorrect(boolean flagIsCorrect) {
		this.flagIsCorrect = flagIsCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public short getOrder() {
		return order;
	}

	public void setOrder(short order) {
		this.order = order;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public boolean equals(Object obj) {
		
		Clause in = (Clause) obj;
		
		return in.getId() == id;
	}
	
}
