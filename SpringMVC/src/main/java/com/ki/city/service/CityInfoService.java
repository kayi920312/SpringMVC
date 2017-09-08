package com.ki.city.service;

import java.util.List;
import java.util.Map;

import com.ki.city.model.CityInfo;
/**
 * 城市信息Service
 * @author kayi9
 *
 */
public interface CityInfoService {

	/**
	 * 根据参数查询城市信息
	 * @param param
	 * @return
	 */
	List<CityInfo> queryCitiesByParam(Map<String, Object> param);
	
	/**
	 * 根据参数查询城市，包含其所属省份信息
	 * @param param
	 * @return
	 */
	List<CityInfo> queryCitiesWithProvinceByParam(Map<String, Object> param);
	
}
