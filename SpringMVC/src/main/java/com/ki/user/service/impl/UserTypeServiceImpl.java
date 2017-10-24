package com.ki.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ki.user.dao.UserTypeDao;
import com.ki.user.model.UserType;
import com.ki.user.service.UserTypeService;

@Service("userTypeServiceImpl")
public class UserTypeServiceImpl implements UserTypeService {

	@Resource(name="userTypeDao")
	private UserTypeDao userTypeDao;
	
	@Override
	public List<UserType> queryUserTypeByParam(Map<String, Object> param) {
		return userTypeDao.queryUserTypeByParam(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public int addNewUserType(UserType userType){
		return userTypeDao.addNewUserType(userType);
	}

}
