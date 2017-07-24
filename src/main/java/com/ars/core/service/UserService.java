package com.ars.core.service;

import com.ars.core.bean.User;

public interface UserService {

	User findByNameOrEmail(User user);
	public void save(User user);
}
