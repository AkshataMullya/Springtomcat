package com.websystique.springmvc.service;

import java.sql.SQLException;
import java.util.List;

import com.websystique.springmvc.model.User;



public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user) throws SQLException;
	
	void updateUser(User user) throws SQLException;
	
	void deleteUserById(long id) throws SQLException;

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
}
