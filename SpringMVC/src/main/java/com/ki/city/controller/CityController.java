package com.ki.city.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ki.city.model.CityInfo;
import com.ki.city.service.CityInfoService;

import util.GsonUtil;

@Controller
public class CityController {
	
	@Resource(name="cityInfoServiceImpl")
	private CityInfoService cityInfoService;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request){
		
	}
	
	/**
	 * 
	 * @param cityId
	 * @param cityName
	 * @return
	 */
	@RequestMapping(value="/city")
	@ResponseBody
    public String getCityByParam(HttpServletRequest request, Model model) {
		
		String cityId =  request.getParameter("cityId");
		System.out.println("index-test");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cityId", cityId);
		List<CityInfo> list = cityInfoService.queryCitiesWithProvinceByParam(param);
        return GsonUtil.toJson(list);
    }
	
//	@ModelAttribute
	public String addTest(){
		System.out.println("@ModelAttribute-test");
		return "@ModelAttribute-test";
	}
	
}
