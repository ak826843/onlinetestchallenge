package com.onlinetestchallenge.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinetestchallenge.components.LoginService;
import com.onlinetestchallenge.components.QuestionaryService;
import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.User;
import com.onlinetestchallenge.repositories.ExamRepository;
import com.onlinetestchallenge.repositories.UserRepository;


@Controller
public class LoginController {

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired QuestionaryService questionaryService;
	
	@Autowired LoginService loginService;
	
	@RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
	public String loginForm(@RequestParam(value="name", required=false, defaultValue="General") String name, Model model){
		
		model.addAttribute("exam", examRepository.findByName(name));
        
		model.addAttribute("user", new User());
		
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginSubmit(@Valid User user, BindingResult bindingResultUser, Exam exam, Model model) {
        
		if (bindingResultUser.hasErrors()) {
			model.addAttribute("exam", examRepository.findOne(exam.getId()));
            return "login";
        }else{
        	if(!loginService.isSatisfied(user)){
        		model.addAttribute("err", "Username or Password invalid");
        		model.addAttribute("exam", examRepository.findOne(exam.getId()));
        		return "login";
        	}
        }
		
		questionaryService.setCurrentUserAndCurrentExam(userRepository.findByuserName(user.getUserName()), examRepository.findOne(exam.getId()));
		
		return "redirect:/question";
    }

}
