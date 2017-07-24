package com.ars.bean;


import javax.persistence.Entity;

import org.junit.Test;

import com.ars.core.bean.User;
import com.ars.junit.SpringJunitTest;


public class TestUser extends SpringJunitTest {

	
	@Test
	public void testAnotation(){
			Entity annotation = User.class.getAnnotation(Entity.class);
			String name = annotation.name();
			System.out.println(name);
			}
}
