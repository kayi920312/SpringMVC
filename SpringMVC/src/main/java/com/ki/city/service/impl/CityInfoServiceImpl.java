package com.ki.city.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ki.city.dao.CityInfoDao;
import com.ki.city.model.CityInfo;
import com.ki.city.service.CityInfoService;

@Service("cityInfoServiceImpl")
public class CityInfoServiceImpl implements CityInfoService {

	@Resource(name="cityInfoDao")
	private CityInfoDao cityInfoDao;
	
	private static Logger logger= Logger.getLogger(CityInfoServiceImpl.class);
	
	/**
	 * 参数查询城市信息
	 */
	public List<CityInfo> queryCitiesByParam(Map<String, Object> param) {
		List<CityInfo> cities = null;
		try{
			cities = cityInfoDao.queryCitiesByParam(param);
		}catch(Exception e){
			logger.info("参数查询城市信息: "+e.getMessage());
		}
		return cities;
	}

	/**
	 * 参数查询城市，包含其所属省份信息
	 */
	public List<CityInfo> queryCitiesWithProvinceByParam(Map<String, Object> param) {
		List<CityInfo> cities = null;
		try{
			cities = cityInfoDao.queryCitiesWithProvinceByParam(param);
		}catch(Exception e){
			logger.info("参数查询城市，包含其所属省份信息: "+e.getMessage());
		}
		return cities;
	}

}
