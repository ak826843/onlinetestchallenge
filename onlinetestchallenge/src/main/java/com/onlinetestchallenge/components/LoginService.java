package com.onlinetestchallenge.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinetestchallenge.models.User;
import com.onlinetestchallenge.repositories.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;
	
	public boolean isSatisfied(User user){
		User userDB = userRepository.findByuserName(user.getUserName());
		
		return (userDB != null && userDB.getPassword().equals(user.getPassword()));
	}
	
}
