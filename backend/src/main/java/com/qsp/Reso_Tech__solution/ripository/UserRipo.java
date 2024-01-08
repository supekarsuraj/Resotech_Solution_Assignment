package com.qsp.Reso_Tech__solution.ripository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.qsp.GalaxyOrder.dto.SignUp;
import com.qsp.Reso_Tech__solution.dto.User;

public interface UserRipo extends JpaRepository<User, Integer> {
	

//	@Query("SELECT name,password FROM user  WHERE name=?1 AND password=?1")
//	User findByNameAndPassword(String name,String password);
	@Query("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
	User findByUsernameAndPassword(@Param("name") String name, @Param("password") String password);

	
	@Query("SELECT e FROM User e WHERE e.name = :name AND e.password = :password")
	User findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

//	User findUserById(int id);
}
