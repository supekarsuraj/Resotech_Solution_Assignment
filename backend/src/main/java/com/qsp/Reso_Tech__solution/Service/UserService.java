package com.qsp.Reso_Tech__solution.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qsp.Reso_Tech__solution.dao.UserDao;
import com.qsp.Reso_Tech__solution.dto.User;
import com.qsp.Reso_Tech__solution.ripository.UserRipo;
import com.qsp.Reso_Tech__solution.util.ResponseStructure;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	public UserRipo ripo;
	
	
	public ResponseEntity<ResponseStructure<User>> signUp(User user) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
		structure.setMessage("User Sign Up Successful");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(userDao.signup(user));
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
	} 
	

	public ResponseEntity<ResponseStructure<User>>login(User user) {
	String name=user.getName();
//	System.out.println(name);
  String password=user.getPassword();
//	System.out.println(password);
	User abc = userDao.login(name, password);
				if (abc != null) {
					
					if(name.equals(abc.getName())&& password.equals(abc.getPassword()))
					{
						ResponseStructure<User> structure = new ResponseStructure<User>();
						structure.setMessage("User Login Successful");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(userDao.login(name,password));
						return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
						
					}
					else {
						ResponseStructure<User> structure = new ResponseStructure<User>();
						structure.setMessage("User Login Failed");
						structure.setStatus(HttpStatus.NOT_FOUND.value());
						structure.setData(user);
						return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
						
					}
			

		} else {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setMessage("User Login Failed");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(userDao.login(name,password));
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		
	}


}





