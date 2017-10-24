package com.ki.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ki.user.model.CertType;

@Repository("certTypeDao")
public interface CertTypeDao {
	
	/**
	 * @描述 参数查询证件类型
	 * @param param
	 * @return
	 */
	List<CertType> queryCertTypeByParam(Map<String, Object> param);
	
	/**
	 * @描述 新增证件类型
	 * @param certType
	 * @return
	 */
	int addNewCertType(CertType certType); 
}
