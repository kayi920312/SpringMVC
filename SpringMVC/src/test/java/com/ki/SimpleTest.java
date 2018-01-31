package com.ki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SimpleTest {

	public static void main(String[] args) {
	
		/*try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream("D:\\test.zip"));
			ZipEntry ze = null;
			while((ze = zis.getNextEntry()) != null){
				System.out.println(ze.getName()+": "+ze.getCrc());
				zis.closeEntry();
			}
			zis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}*/
	
		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:\\testWrite.zip"));
			String[] files = new String[]{"D:\\20170724094838\\20170724094838_1.wav", "D:\\20170724094838\\20170724094838_2.wav", "D:\\20170724094838\\20170724094838_3.wav"};
			File file = null;
			ZipEntry ze = null;
			byte[] b = new byte[1024];
			int read = 0;
			for (String string : files) {
				file = new File(string);
				ze = new ZipEntry(file.getName());				
				zos.putNextEntry(ze);
				zos.closeEntry();
			}
			zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
