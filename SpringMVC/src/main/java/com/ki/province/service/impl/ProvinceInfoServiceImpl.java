package com.ki.province.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ki.province.dao.ProvinceInfoDao;
import com.ki.province.model.ProvinceInfo;
import com.ki.province.service.ProvinceInfoService;

@Service("provinceInfoServiceImpl")
public class ProvinceInfoServiceImpl implements ProvinceInfoService {

	@Resource(name="provinceInfoDao")
	private ProvinceInfoDao provinceInfoDao;
	
	private static Logger logger= Logger.getLogger(ProvinceInfoServiceImpl.class);
	
	/**
	 * 参数查询省份信息
	 */
	public List<ProvinceInfo> queryProvincesByParam(Map<String, Object> param) {
		List<ProvinceInfo> provinces = null;
		try{
			provinces = provinceInfoDao.queryProvincesByParam(param);
		}catch(Exception e){
			logger.info("参数查询省份信息: "+e.getMessage());
		}
		return provinces;
	}

	/**
	 * 参数查询省份，包含其城市信息
	 */
	public List<ProvinceInfo> queryProvincesWithCitiesByParam(Map<String, Object> param) {
		List<ProvinceInfo> provinces = null;
		try{
			provinces = provinceInfoDao.queryProvincesWithCitiesByParam(param);
		}catch(Exception e){
			logger.info("参数查询省份，包含其城市信息: "+e.getMessage());
		}
		return provinces;
	}

}
