package com.onlinetestchallenge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlinetestchallenge.models.Exam;
import com.onlinetestchallenge.models.User;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {
	public Exam findByName(String name);
}
