/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: ServiceTest.java 
 * @Prject: ars
 * @Package: com.ars.service 
 * @Description: 
 * @author: zw
 * @date: 2017年4月4日 下午9:11:47 
 * @version: V1.0   
 */
package com.ars.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ars.core.bean.User;
import com.ars.core.service.UserService;
import com.ars.junit.SpringJunitTest;

/** 
 * @ClassName: ServiceTest 
 * @Description: 
 * @author: zw
 * @date: 2017年4月4日 下午9:11:47  
 */
public class ServiceTest extends SpringJunitTest {

@Autowired
private UserService userService;
	/**
	 * 
	 * @Title: saveUser 
	 * @Description: 
	 * @author: zw
	 * void
	 */
	@Test
	public void saveUser(){
		User user=new User();
		user.setUsername("admin");
		user.setPassword("123");
		User user1 =new User();
		user1.setUsername("nike");
	/*	User user2 = userService.findByNameOrEmail(user1);
		System.out.println(user2.getPassword());*/
		userService.save(user);
		
	}
}
