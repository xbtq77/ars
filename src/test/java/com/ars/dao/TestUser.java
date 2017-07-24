package com.ars.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ars.core.bean.User;
import com.ars.core.dao.UserDao;
import com.ars.junit.SpringJunitTest;

public class TestUser extends SpringJunitTest{
	@Autowired
	private UserDao userDao;
	@Autowired
	SessionFactory sessionFactory;
	@Test
	public void readById(){
		User user = userDao.findObjectByID(1);
		System.out.println(user.getUsername());
	}
	@Test
	@Transactional(readOnly=false)
	public void  insert(){
	/*	User user=new User();
		user.setRealname("hello");
		user.setUsername("hello");
		userDao.save(user);*/
		
	}
	@Test
	public void testSession(){
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, 1);
		System.out.println(user.getUsername());
	}
}
