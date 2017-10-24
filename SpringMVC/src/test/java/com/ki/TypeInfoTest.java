package com.ki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ki.test.base.BaseDaoBeanSupport;
import com.ki.user.model.CertType;
import com.ki.user.model.UserType;
import com.ki.user.service.CertTypeService;
import com.ki.user.service.UserTypeService;

public class TypeInfoTest extends BaseDaoBeanSupport{


//	@Test
	public void queryCertTypeByParam() {
		CertTypeService certTypeService = (CertTypeService) getBean("certTypeServiceImpl");
		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("id", 1);
		List<CertType> certTypeList = certTypeService.queryCertTypeByParam(param);
		System.out.println(certTypeList.toString());
	}

//	@Test
	public void queryUserTypeByParam() {
		UserTypeService userTypeService = (UserTypeService) getBean("userTypeServiceImpl");
		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("id", 1);
		List<UserType> userTypeList = userTypeService.queryUserTypeByParam(param);
		System.out.println(userTypeList.toString());
	}
	
	@Test
	public void addNewCertType(){
		
		CertType certType = new CertType("回乡证");
		CertTypeService certTypeService = (CertTypeService) getBean("certTypeServiceImpl");
		int addCount = 0;
		
		try{
			addCount = certTypeService.addNewCertType(certType);
			System.out.println(addCount);
		}catch(Exception e){
			System.out.println("异常");
		}
		
		queryCertTypeByParam();
		queryUserTypeByParam();
	}
}
