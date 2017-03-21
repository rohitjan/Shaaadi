package com.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.pojo.Login;
import com.pojo.Register;

public interface AdminService {
	
	public Register checkApprovedUsers(String userName,String userPassword);
	public String registerUser(Register register);

}
