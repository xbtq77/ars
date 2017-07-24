package com.ars.core.dao.impl;

import org.springframework.stereotype.Repository;
import com.ars.core.bean.User;
import com.ars.core.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonDaoImpl< User> implements UserDao{

}
