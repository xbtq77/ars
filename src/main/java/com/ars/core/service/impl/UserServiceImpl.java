package com.ars.core.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ars.core.bean.User;
import com.ars.core.dao.UserDao;
import com.ars.core.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public User findByNameOrEmail(User user) {
		String condition=" and  o.username=? or o.email=?";
		Object[] params={user.getUsername(),user.getUsername()};
		List<User>list=userDao.findCollectionByConditionNoPage(condition, params, null);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Transactional(readOnly=false)
	public void save(User user){
	
		userDao.save(user);
	}
}
