package com.ki.city.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ki.city.model.CityInfo;
/**
 * 城市信息Dao
 * @author kayi9
 *
 */
@Repository("cityInfoDao")
public interface CityInfoDao {
	
	//根据参数查询城市信息
	List<CityInfo> queryCitiesByParam(Map<String, Object> param);
	//根据参数查询城市，包含其所属省份信息
	List<CityInfo> queryCitiesWithProvinceByParam(Map<String, Object> param);
}
