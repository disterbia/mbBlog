package com.cos.mbBlog.repository;


import com.cos.mbBlog.model.User;

public interface UserRepository {
	User findByUsernameAndPassword(User user);
	void save(User user);
	void updateUserProFile(User user);
}
