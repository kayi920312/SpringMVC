package com.ki.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{

	private String datePattern;
	private SimpleDateFormat simpleDateFormat;
	
	public DateFormatter(String datePattern){
		this.datePattern = datePattern;
		simpleDateFormat = new SimpleDateFormat(this.datePattern);
		simpleDateFormat.setLenient(false);
	}
	
	// Date to String
	public String print(Date date, Locale locale) {
		return simpleDateFormat.format(date);
	}

	//String to Date
	public Date parse(String text, Locale locale) throws ParseException {
		try{
			System.out.println("开始转换日期");
			return simpleDateFormat.parse(text);
		}catch(ParseException e){
			System.out.println("时间格式转换失败。。。");
			throw new IllegalArgumentException("时间格式转换失败");
		}
	}

}
