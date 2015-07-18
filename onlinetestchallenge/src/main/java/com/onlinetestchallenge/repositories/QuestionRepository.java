package com.onlinetestchallenge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	//public Exam findByName(String name);
}
