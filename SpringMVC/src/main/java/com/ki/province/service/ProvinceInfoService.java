package com.ki.province.service;

import java.util.List;
import java.util.Map;

import com.ki.province.model.ProvinceInfo;
/**
 * 省份信息Service
 * @author kaiying.chen
 *
 */
public interface ProvinceInfoService {
	
	//参数查询省份信息 
	List<ProvinceInfo> queryProvincesByParam(Map<String, Object> param); 
	//参数查询省份，包含其城市信息
	List<ProvinceInfo> queryProvincesWithCitiesByParam(Map<String, Object> param); 
}
