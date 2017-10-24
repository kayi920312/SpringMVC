package com.ki.user.service;

import java.util.List;
import java.util.Map;

import com.ki.user.model.UserType;

public interface UserTypeService {

	/**
	 * @描述 参数查询旅客类型
	 * @param param
	 * @return
	 */
	List<UserType> queryUserTypeByParam(Map<String, Object> param);
	
	/**
	 * @描述 新增旅客类型
	 * @param certType
	 * @return
	 */
	int addNewUserType(UserType userType); 
	
}
