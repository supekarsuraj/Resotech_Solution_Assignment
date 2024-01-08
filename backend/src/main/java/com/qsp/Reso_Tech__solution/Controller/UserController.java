package com.qsp.Reso_Tech__solution.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qsp.Reso_Tech__solution.Service.UserService;
import com.qsp.Reso_Tech__solution.dto.User;
import com.qsp.Reso_Tech__solution.util.ResponseStructure;

@Controller
@RestController
public class UserController {
	@Autowired
	private UserService ser;
	@PostMapping("/signup")
	 public ResponseEntity<ResponseStructure<User>> signUp(@RequestBody User user)
	 {	
		return ser.signUp(user);
	 }


	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestBody User user) { 
		return ser.login(user);
	  } 
	
	
}
