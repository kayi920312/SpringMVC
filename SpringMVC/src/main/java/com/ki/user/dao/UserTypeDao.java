package com.ki.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ki.user.model.UserType;

@Repository("userTypeDao")
public interface UserTypeDao {
	
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
