package com.ki.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	private String datePattern;
	
	public StringToDateConverter(String datePattern){
		this.datePattern = datePattern;
	}
	
	public Date convert(String s) {
		try{
			System.out.println("开始转换日期");
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			dateFormat.setLenient(false);
			return dateFormat.parse(s);
		}catch(Exception e){
			System.out.println("时间格式转换失败。。。");
			throw new IllegalArgumentException("时间格式转换失败");
		}
	}


}
