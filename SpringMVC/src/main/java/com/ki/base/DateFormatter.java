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
		simpleDateFormat.setLenient(false);	//true则不太严格，false则严格: 宽松的，仁慈的
	}
	
	// Date to String
	public String print(Date date, Locale locale) {
		return simpleDateFormat.format(date);
	}

	//String to Date
	public Date parse(String text, Locale locale) throws ParseException {
		try{
			return simpleDateFormat.parse(text); 
		}catch(ParseException e){
			System.out.println("时间格式转换失败: "+e.toString());
			throw new IllegalArgumentException("时间格式转换失败");
		}
	}

}
