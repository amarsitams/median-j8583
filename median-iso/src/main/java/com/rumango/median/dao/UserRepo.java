package com.rumango.median.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.median.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	List<User> findByUserId(String userId);
}
