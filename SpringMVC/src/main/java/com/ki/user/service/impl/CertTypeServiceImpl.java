package com.ki.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ki.user.dao.CertTypeDao;
import com.ki.user.model.CertType;
import com.ki.user.model.UserType;
import com.ki.user.service.CertTypeService;
import com.ki.user.service.UserTypeService;

@Service("certTypeServiceImpl")
public class CertTypeServiceImpl implements CertTypeService {

	@Resource(name="certTypeDao")
	private CertTypeDao certTypeDao;
	
	@Resource(name="userTypeServiceImpl")
	private UserTypeService userTypeService;
	
	@Override
	public List<CertType> queryCertTypeByParam(Map<String, Object> param) {
		return certTypeDao.queryCertTypeByParam(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public int addNewCertType(CertType certType){
		int addCount = 0;
		addCount = certTypeDao.addNewCertType(certType);	
		
		UserType userType = new UserType();
		userTypeService.addNewUserType(userType);
		/*try{
		}catch(Exception e){
			System.out.println("userType 新增异常");
		}*/
		
		return addCount;
	}

}
