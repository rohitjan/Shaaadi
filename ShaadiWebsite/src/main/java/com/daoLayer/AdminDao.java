package com.daoLayer;

import com.pojo.Login;
import com.pojo.Register;

public interface AdminDao {

	public Register checkApprovedUsers(String userName, String userPassword);
	public String registerUser(Register register);
}
