package com.ki.vedio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VedioControler {
	
	@RequestMapping(value="/v/test")
	public void vedio(HttpServletRequest request, HttpServletResponse response){
		
		String path = request.getParameter("path");
		if(path==null||"".equals(path)){
			return;
		}
		
		File infile = new File(path);  
		
		//设置返回头信息
		response.setHeader("Content-Disposition", "attachment;filename=\""+ infile.getName());
		response.setHeader("Accept-Ranges", "bytes");
		response.setContentType("video/mpeg4");
		response.setDateHeader("Last-Modified", infile.lastModified());
	    response.setDateHeader("Expires", System.currentTimeMillis() + (1000 * 60 * 60 * 24));//默认客户端有效时间为一天，减少请求服务端的数量
	    
	    long length = infile.length(); //数据总长度
	    long start = 0;
	    long end = length - 1; //请求范围的默认结束值，字节计算从0开始，要减一
	    
	    String[] headRange = null;
	    String headRangeHeader = request.getHeader("Range");
	    if(headRangeHeader!=null&&!"".equals(headRangeHeader)){
	    	
	    	if("1".equals(request.getParameter("point"))){
	 	    	//opint=1标识是浏览器，使用206断点，否则不设置206
	 	    	response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); //206
	 	    }
	    	
	    	headRange = headRangeHeader.split("=")[1].split("-"); //请求的范围 
	    	start = Long.valueOf(headRange[0]);//请求范围的开始值
	    	if(headRange.length>1){
	    		end = Long.valueOf(headRange[1]);//若请求头有请求结束值，则重新赋值
		    }
	    }
	    
	    long contentLength = end - start + 1;//请求的数据长度

		//设置文件头的文件长度
	    response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
	    response.setHeader("Content-Length", String.format("%s", contentLength));
//	    response.setHeader("Etag", "W/\"9767057-1323779115364\"");
	    
	    RandomAccessFile raf = null;
	    ServletOutputStream fos = null;
		
		try {
			raf = new RandomAccessFile(infile, "r");
			raf.seek(start); //从请求的开始值开始，跳过不需要的字节数
			
			fos = response.getOutputStream();
			
			byte[] buffer = new byte[1024];
			int c;
		    while ((c = raf.read(buffer))!=-1) {
		    	fos.write(buffer, 0, c);
		    }
			fos.flush();
		}catch (Exception e) {
			if(e.getMessage().contains("Software caused connection abort")){
				//客户端主动关闭，不需要处理
			}else{
				System.out.println("发生异常："+e.toString());
			}
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					System.out.println("RandomAccessFile 关闭异常："+e.toString());
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					if(e.getMessage().contains("Software caused connection abort")){
						//客户端主动关闭，不需要处理
					}else{
						System.out.println("ServletOutputStream 关闭异常："+e.toString());
					}
				}
			}
		}
	}
}
