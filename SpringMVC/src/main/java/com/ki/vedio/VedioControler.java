package com.ki.vedio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VedioControler {
	
	@RequestMapping(value="/v/test")
	public void vedio(HttpServletRequest request, HttpServletResponse response){

		File infile = new File("D:/SpringMVC/Test/1.mp4");  
		
		//设置返回头信息
		response.setContentType("video/mpeg4");
		response.setHeader("Content-Disposition", "attachment;filename=\""+ infile.getName());
		response.setDateHeader("Last-Modified", infile.lastModified());
	    response.setHeader("Accept-Ranges", "bytes");
	    response.setDateHeader("Expires", System.currentTimeMillis() + (1000 * 60 * 60 * 24));//默认客户端有效时间为一天，减少请求服务端的数量
	    
	    long length = infile.length(); //数据总长度
	    long end = length - 1; //请求范围的默认结束值
	    
	    String[] headRange = request.getHeader("Range").split("=")[1].split("-"); //请求的范围
	    long start = Long.valueOf(headRange[0]);//请求范围的开始值
	    
	    if(headRange.length>1){
	    	end = Long.valueOf(headRange[1]);//若请求头有请求结束值，则重新赋值
	    }
	    
	    long contentLength = end - start + 1;//请求的数据长度
	    
//			    System.out.println("start="+start+", end="+end+", contentLength="+contentLength);
	    
	    //设置文件头的文件长度
	    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); 
	    
	    response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
	    response.setHeader("Content-Length", String.format("%s", contentLength));
//			    response.setHeader("Etag", "W/\"9767057-1323779115364\"");
	    
	    InputStream fis = null;
		OutputStream fos = null;
		
		try {
			fis = new FileInputStream(infile);
			fis.skip(Long.valueOf(start)); //从请求的开始值开始，跳过不需要的字节数
			
			fos = response.getOutputStream();
			byte[] buffer = new byte[1024*1024];
			int c;
		    while ((c = fis.read(buffer))!=-1) {
		    	fos.write(buffer, 0, c);
		    }
			fos.flush();
		}catch (ClientAbortException cae){
			
		}catch (SocketException se){
			
		}catch (IOException e) {
//			logger.info("文件下载失败："+e.toString());
			System.out.println("文件下载失败："+e.toString());
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
}
