package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daoLayer.AdminDao;
import com.daoLayer.UserDao;
import com.pojo.Login;
import com.pojo.Register;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao admindao;

	public Register checkApprovedUsers(String userName, String userPassword) {
	
		return admindao.checkApprovedUsers(userName, userPassword);
	}

	public String registerUser(Register register) {
		
		return admindao.registerUser(register);
	}

}
