package com.websystique.springmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.User;

public class ConnectionDao {
	Connection con = null;
	public Connection RetriveConnection() {
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection(  
		"jdbc:mysql://database1.cikrt1wxrd3j.us-east-1.rds.amazonaws.com:3306/springdata","admin","^TFGHY6tfghy");
		}catch(Exception e){ System.out.println(e);}
	return con;  
	}
	
	public void addUser(User user) throws SQLException {
	
		String query="insert into userinfo(username,address,email) values('"+user.getUsername()+"','"+user.getAddress()+"','"+user.getEmail()+"')";
		Statement stmt=con.createStatement();  
		stmt.executeUpdate(query);
		
	}
	public List<User> getUserList(Connection conn){
		if(conn == null) {
			conn = RetriveConnection();
		}
		List<User> userList = new ArrayList<>();
		try {
		Statement stmt=conn.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from userinfo");  
		while(rs.next())  {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setAddress(rs.getString(3));
			user.setEmail(rs.getString(4));
			userList.add(user);
		}
		System.out.println("list is: "+userList.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
		//con.close();  
		
	}
	
	public void updateUser(User user) throws SQLException {
		PreparedStatement stmt=con.prepareStatement("update userinfo set username=?,address=?,email=? where id=?");  
		//1 specifies the first parameter in the query  
		stmt.setString(1,user.getUsername());
		stmt.setString(2,user.getAddress());
		stmt.setString(3,user.getEmail());
		stmt.setLong(4,user.getId());
		int i=stmt.executeUpdate();  

		
	}

	public void deleteUser(long id) throws SQLException {
		PreparedStatement stmt=con.prepareStatement("delete from userinfo where id=?");    
		stmt.setLong(1,id);
		int i=stmt.executeUpdate(); 
		
	}

}
