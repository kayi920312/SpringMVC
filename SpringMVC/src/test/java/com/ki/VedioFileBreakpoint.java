package com.ki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VedioFileBreakpoint {

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File file = new File("D:/SpringMVC/Test/1.mp4");
		System.out.println(file.length());
		long stopLength = file.length()/2;
		System.out.println(stopLength);
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(new File("D:/SpringMVC/11.mp4"));
			int read = -1;
			long readed = 0;
			byte[] temp = new byte[1024];
			while((read = fis.read(temp))!=-1){
				fos.write(temp, 0, read);
				readed = readed + temp.length;
				System.out.println("写入了["+readed+"]字节");
				/*if(readed>stopLength){
					System.out.println("已写"+readed+"，大于"+stopLength+"，结束复制");
					return;
				}*/
				Thread.sleep(500);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
