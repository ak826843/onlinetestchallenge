package com.onlinetestchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinetestchallenge.components.QuestionaryService;
import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.Score;
import com.onlinetestchallenge.repositories.ScoreRepository;


@Controller
public class ScoreController {

	@Autowired QuestionaryService questionaryService;
	
	@Autowired ScoreRepository scoreRepository;
	
	@RequestMapping("/score")
	public String score(Model model){
		
		Score newScore = new Score();
		
		Exam exam = questionaryService.getCurrentExam();
		
		newScore.setUser(questionaryService.getCurrentUser());
		
		newScore.setFlgIsAproved(questionaryService.score() >= exam.getPassScore());
		
		newScore.setGrade(questionaryService.score());
		
		newScore.setTotalExam(exam.getQuestions().size());
		
		newScore.setNameExam(exam.getName());
		
		model.addAttribute("score", scoreRepository.save(newScore));
		
		model.addAttribute("scores", scoreRepository.findByUser(questionaryService.getCurrentUser()));
		
		questionaryService.clean();
		
        return "score";
	}

}
