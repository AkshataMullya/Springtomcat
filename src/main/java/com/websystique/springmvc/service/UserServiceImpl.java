package com.websystique.springmvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.ConnectionDao;
import com.websystique.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	ConnectionDao connDao = new ConnectionDao();
	Connection conn = null;
	
	static{
		//users = populateDummyUsers();
	}
	
	public void getConnection() {
		conn = connDao.RetriveConnection();
		users= connDao.getUserList(conn);
	}

	public List<User> findAllUsers() {
	//	return users;
		connDao.RetriveConnection();
		users= connDao.getUserList(conn);
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) throws SQLException {
		User u=new User();
		u.setId(counter.incrementAndGet());
		u.setUsername(user.getUsername());
		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		connDao.addUser(u);
		users= connDao.getUserList(conn);
	}

	public void updateUser(User user) throws SQLException {
//		int index = users.indexOf(user);
//	    users.set(index, user);
		User u=new User();
         u.setId(user.getId());
		u.setUsername(user.getUsername());
		System.out.println(user.getUsername());
		u.setAddress(user.getAddress());
		u.setEmail(user.getEmail());
		connDao.updateUser(u);
		users= connDao.getUserList(conn);
	}

	public void deleteUserById(long id) throws SQLException {
		connDao.deleteUser(id);
		
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//		    User user = iterator.next();
//		    if (user.getId() == id) {
//		        iterator.remove();
//		    }
//		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
//		users.clear();
		
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}

}
