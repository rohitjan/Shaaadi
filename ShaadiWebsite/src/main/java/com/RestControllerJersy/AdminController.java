package com.RestControllerJersy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.Login;
import com.pojo.Register;
import com.service.AdminService;


@RestController
@RequestMapping("/adminController")
public class AdminController {
	

	@Autowired
	AdminService AdminService;
	@RequestMapping(value="/checklogin/{username}/{password}",method=RequestMethod.POST)
	public Register CheckApprovedUsers(@PathVariable("username") String username,@PathVariable("password") String password){
		
		System.out.println("inside checklogin controller" +"UserName :"+username+ " Password :"+password);		
		return AdminService.checkApprovedUsers(username,password);
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody Register register){
	
		String response="{\"msg\":\"True\"}";
		System.out.println("inside registerUser controller........"+register.getUsername());		
		return AdminService.registerUser(register);
	}
	
	
}
