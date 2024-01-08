package com.qsp.Reso_Tech__solution.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qsp.Reso_Tech__solution.dto.User;
import com.qsp.Reso_Tech__solution.ripository.UserRipo;
@Repository
public class UserDao {
	@Autowired
	private UserRipo userRipo;
		
	public User signup(User user)
	{
		
		return userRipo.save(user);
	}
	public User login(String name,String password)
	{
		System.out.println("run login doa");
		return userRipo.findByUsernameAndPassword(name, password);
	}	
	


	
}
