package com.onlinetestchallenge.controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetestchallenge.components.QuestionaryService;
import com.onlinetestchallenge.models.Clause;
import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.Question;
import com.onlinetestchallenge.repositories.ExamRepository;
import com.onlinetestchallenge.repositories.QuestionRepository;


@Controller
public class QuestionController {

	@Autowired ExamRepository examRepository;
	
	@Autowired QuestionRepository questionRepository;
	
	@Autowired QuestionaryService questionaryService;
	
	@RequestMapping(value= {"/question"}, method=RequestMethod.GET)
	public String questionForm(Model model, @RequestParam(value="order", required=false, defaultValue="0") String number){
		Exam exam = questionaryService.getCurrentExam();
		
		Question question;
		
		try{
			question = ((Question) (new ArrayList<Question>(exam.getQuestions())).get(Integer.parseInt(number)));
		}catch(Exception ex){
			question = ((Question) (new ArrayList<Question>(exam.getQuestions())).get(0));
		}
		
		
		model.addAttribute("question", question);
		
		model.addAttribute("questions", exam.getQuestions());
		
		model.addAttribute("clausesAnswered", questionaryService.getAnswers().get(question));
		
        return "question";
	}
	
	@RequestMapping(value= {"/question"}, method=RequestMethod.POST)
	public String questionSubmitNext(Question question, Model model, @RequestParam(value="order", required=false, defaultValue="0") String number){
		
		List<Clause> clausesVO = question.getClauses();
		
		List<Clause> clausesAnswered = new ArrayList<>();
		
		if(clausesVO != null){
			for (Clause clause : clausesVO) {
				if(clause.getId() != 0){
					clausesAnswered.add(clause);
				}
			}
		}
		
		questionaryService.putAnswer(question, clausesAnswered);
		
		int page = (int)question.getOrder() + 1;
		
		return "redirect:/question?order="+ page;		
	}
	
	@RequestMapping(value= {"/questionFinalize"}, method=RequestMethod.POST)
	public @ResponseBody boolean questionSubmitFinalize(){
		
		return questionaryService.isDone();		
	}
	
	@RequestMapping(value= {"/verifytime"}, method=RequestMethod.POST)	
	public @ResponseBody boolean questionVerifyTime(){
		
		Exam exam = questionaryService.getCurrentExam();
		
		String mask = "HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormat.forPattern(mask);
		
		LocalTime duration = new LocalTime(formatter.parseDateTime(exam.getDuration()));
		
		LocalTime start = questionaryService.getTimeTestBegin();
		
		LocalTime elapsed = (new LocalTime()).minusSeconds(start.getSecondOfMinute()).minusMinutes(start.getMinuteOfHour()).minusHours(start.getHourOfDay());
		
		if(elapsed.isAfter(duration)){
			return false;
		}else{
			return true;
		}
				
	}
}
