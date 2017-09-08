package com.ki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ki.province.model.ProvinceInfo;
import com.ki.province.service.ProvinceInfoService;
import com.ki.test.base.BaseDaoBeanSupport;

public class ProvinceInfoTest extends BaseDaoBeanSupport{


//	@Test
	public void queryProvincesByParam() {
		ProvinceInfoService provinceService = (ProvinceInfoService) getBean("provinceServiceImpl");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 1);
		List<ProvinceInfo> provinces = provinceService.queryProvincesByParam(param);
		System.out.println();
	}

	@Test
	public void queryProvincesWithCitiesByParam() {
		ProvinceInfoService provinceService = (ProvinceInfoService) getBean("provinceServiceImpl");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", 19);
		List<ProvinceInfo> provinces = provinceService.queryProvincesWithCitiesByParam(param);
	}
}
