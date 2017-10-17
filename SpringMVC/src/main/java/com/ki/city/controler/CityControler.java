package com.ki.city.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ki.city.model.CityInfo;
import com.ki.city.service.CityInfoService;

import util.GsonUtil;

@Controller
@RequestMapping("/city")
public class CityControler {
	
	@Autowired
	private CityInfoService cityService;
	
	@ResponseBody
	@RequestMapping("/queryCitiesByParam")
	public String queryCitiesByParam(Integer id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		List<CityInfo> list = cityService.queryCitiesByParam(param);
		return GsonUtil.toJson(list);
	}
}
