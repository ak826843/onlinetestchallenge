package com.onlinetestchallenge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlinetestchallenge.models.Score;
import com.onlinetestchallenge.models.User;
import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
	List<Score> findByUser(User user);
}
