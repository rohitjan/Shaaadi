package com.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojo.Login;
import com.pojo.Register;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	public Register checkApprovedUsers(String userName, String password) {
	
		
		System.out.println("Inside Admin Dao IMPL" +userName+"Password :"+password);
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Register where userName=:userName and password=:password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		List<Register> listuser=query.list();
		if(listuser!=null || !listuser.isEmpty())
		{
			return listuser.get(0);
		}
		return null;
		
	}

   @Transactional
	public String registerUser(Register register) {
	   String response="{\"msg\":\"Successfully Registered\"}";
		System.out.println("Inside Admin Dao registerUser IMPL");
		Session session=sessionFactory.getCurrentSession();
		session.persist(register);
		System.out.println("Successfully added");
	    Login login=new Login();
	    login.setUsername(register.getUsername());
	    login.setPassword(register.getPassword());
	    session.persist(login);
	    System.err.println("Successfully added in User Table");
		return response;
	}

}
