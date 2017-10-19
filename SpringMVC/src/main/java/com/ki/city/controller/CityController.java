package com.ki.city.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ki.city.model.CityInfo;
import com.ki.city.service.CityInfoService;

import util.GsonUtil;

@Controller
@RequestMapping(value="/city")
public class CityController {
	
	private static final Logger loger = Logger.getLogger(CityController.class);
	
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
	@RequestMapping(value="/getCityByParam")
	@ResponseBody
    public String getCityByParam(HttpServletRequest request, Model model) {
		loger.info("getCityByParam");
		String cityId = request.getParameter("cityId");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cityId", cityId);
		List<CityInfo> list = cityInfoService.queryCitiesWithProvinceByParam(param);
		cityInfoService.queryCitiesWithProvinceByParam(param);
        return GsonUtil.toJson(list);
    }
	
	@ModelAttribute
	public String addTest(){
		System.out.println("@ModelAttribute-test");
		return "@ModelAttribute-test";
	}
	
	/*上传图片*/
	@RequestMapping(value="/uploadPic")
	public void uploadPic(MultipartFile file){
		if(file!=null&&!file.isEmpty()){
			
			FileInputStream is = null;
			FileOutputStream os = null;
			File saveFilePath = new File("D:/SpringMVC/Test");
			File saveFile = null;
			String fileName = null;
			
			try {
				is = (FileInputStream) file.getInputStream();
				fileName = file.getOriginalFilename();
				saveFile = new File(saveFilePath, fileName);
				if(!saveFilePath.exists()){
					saveFilePath.mkdirs();
				}
				os = new FileOutputStream(saveFile);
				int read = 0;
				byte[] readTemp = new byte[1024];
				while((read = is.read(readTemp))!=-1){
					os.write(readTemp, 0, read);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(os!=null){
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@RequestMapping(value="/uploadPic2")
	@ResponseBody
	public String uploadPic2(MultipartFile file){
		Map<String, String> result = new HashMap<String, String>();
		if(file!=null&&!file.isEmpty()){
			
			File saveFilePath = new File("D:/SpringMVC/Test", file.getOriginalFilename());
			try {
				file.transferTo(saveFilePath);
				result.put("result_code", "0");
				result.put("result_msg", "上传成功");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return GsonUtil.toJson(result);
	}
	
	/** 
	 * 如果方法返回为 void
	 * 则需要传入 HttpServletResponse 对象
	 * 否则
	 * 方法执行完后
	 * spring默认跳转到 @RequestMapping 的 value 值 /city/pic3.jsp
	 * 这时候会报 404 的错误 */
	@RequestMapping(value="/uploadPic3")
	public void uploadPic3(MultipartFile file){
		Map<String, String> result = new HashMap<String, String>();
		if(file!=null&&!file.isEmpty()){
			
			File saveFilePath = new File("D:/SpringMVC/Test", file.getOriginalFilename());
			try {
				file.transferTo(saveFilePath);
				result.put("result_code", "0");
				result.put("result_msg", "上传成功");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/*下载*/
}
