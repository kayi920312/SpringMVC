package com.ki;

import java.io.File;

public class VedioFileDownTest {

	public static void main(String[] args) {
		File file = new File("D:/SpringMVC/11.mp4");
		while(true){
			System.out.println(file.length());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
