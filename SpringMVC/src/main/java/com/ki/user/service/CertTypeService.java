package com.ki.user.service;

import java.util.List;
import java.util.Map;

import com.ki.user.model.CertType;

public interface CertTypeService {
	
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
