package com.rumango.median.iso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumango.median.iso.entity.User;

public interface UserRepo extends JpaRepository<User, String> {
	List<User> findByUserId(String userId);
}
