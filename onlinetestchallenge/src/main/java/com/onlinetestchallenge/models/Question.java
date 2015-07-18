package com.onlinetestchallenge.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity()
public class Question {
	
	@Id
    @Column(name="id")
    @GeneratedValue
    private long id;
	
	private String tittle;
	
	private String description;
	
	@Column(name = "`order`") 
	private int order;
	
	private boolean flgIsMultipleAnswers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_exam")
	private Exam exam;
	
	@OneToMany(mappedBy="question",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("order")
	private List<Clause> clauses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFlgIsMultipleAnswers() {
		return flgIsMultipleAnswers;
	}

	public void setFlgIsMultipleAnswers(boolean flgIsMultipleAnswers) {
		this.flgIsMultipleAnswers = flgIsMultipleAnswers;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<Clause> getClauses() {
		return clauses;
	}

	public void setClauses(List<Clause> clauses) {
		this.clauses = clauses;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public int numCorrect(){
		int total = 0;
		for (Clause clause : clauses) {
			if(clause.isFlagIsCorrect()){
				total++;
			}
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		
		Question in = (Question) obj;
		
		return in.getId() == id;
	}
	
	@Override
	public int hashCode()
	{
	    return (int) id;
	}
}
