package com.onlinetestchallenge.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinetestchallenge.models.Clause;
import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.Question;
import com.onlinetestchallenge.models.User;
import com.onlinetestchallenge.repositories.QuestionRepository;

@Service
public class QuestionaryService {
	
	
	private Exam currentExam;
	
	private User currentUser;
	
	private LocalTime timeTestBegin;
	
	private Map<Question, List<Clause>> answers = new HashMap<>();
	
	@Autowired QuestionRepository questionaryRepository;

	
	public Map<Question, List<Clause>> getAnswers() {
		return answers;
	}
	
	public void putAnswer(Question question, List<Clause> clausesChecked){		
		answers.get(question).clear();
		answers.get(question).addAll(clausesChecked);
	}
	
	public boolean isDone(){
		
		for (Entry<Question, List<Clause>> entry : answers.entrySet())
		{
			if(entry.getValue().size() == 0){
				return false;
			}
		}
		
		return true;
	}

	public int score(){
		
		int points = 0;

		for (Entry<Question, List<Clause>> entry : answers.entrySet())
		{
			if(entry.getKey().numCorrect() == entry.getValue().size()){
				Question questionDB = questionaryRepository.findOne(entry.getKey().getId());
				
				questionDB.getClauses().removeAll(entry.getValue());
				
				boolean isPoint = true;
				
				for (Clause clause : questionDB.getClauses()) {
					if(clause.isFlagIsCorrect()){
						isPoint = false;
					}
				}
				
				if(isPoint){
					points++;
				}
			}
						
		}
		
		return points;
	}
	
	public Exam getCurrentExam() {
		return currentExam;
	}

	public void setCurrentExam(Exam currentExam) {
		this.currentExam = currentExam;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void setCurrentUserAndCurrentExam(User currentUser, Exam currentExam) {
		this.currentUser = currentUser;
		this.currentExam = currentExam;
		timeTestBegin = new LocalTime();
		initAnwers(currentExam);
	}
	
	private void initAnwers(Exam currentExam){
		for (Question question : currentExam.getQuestions()) {
			answers.put(question, new ArrayList<Clause>());
		}
		
	}
	
	public void clean(){
		this.currentUser = null;
		this.currentExam = null;
		timeTestBegin = new LocalTime();
		answers = new HashMap<>();
	}

	public LocalTime getTimeTestBegin() {
		return timeTestBegin;
	}

	public void setTimeTestBegin(LocalTime timeTestBegin) {
		this.timeTestBegin = timeTestBegin;
	}
	
}
