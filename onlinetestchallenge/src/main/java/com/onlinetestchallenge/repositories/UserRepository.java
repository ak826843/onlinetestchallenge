package com.onlinetestchallenge.repositories;

import org.springframework.data.repository.CrudRepository;

import com.onlinetestchallenge.models.User;


public interface UserRepository extends CrudRepository<User, Long> {
	public User findByuserName(String userName);
}
