package com.ki.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import com.ki.test.base.BaseDaoBeanSupport;

public class ChannelTest2 extends BaseDaoBeanSupport{


	@Test
	public void client() throws IOException{
		
		FileChannel inChannel = FileChannel.open(Paths.get("D:\\nio\\1.jpg"), StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel sChannel = SocketChannel.open();
		sChannel.connect(new InetSocketAddress(8899));
		if(sChannel.finishConnect()){
			while(inChannel.read(buffer)!=-1){
				buffer.flip();
				sChannel.write(buffer);
				buffer.clear();
			}
			
			sChannel.shutdownOutput();
			
			int len = 0;
			while((len=sChannel.read(buffer))!=-1){
				buffer.flip();
				System.out.println(new String(buffer.array(), 0, len));
			}
			
		}
		
		sChannel.close();
		inChannel.close();
	}

	@Test
	public void server() throws IOException {
		
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel.open(Paths.get("D:\\nio\\2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		ssChannel.bind(new InetSocketAddress(8899));
		SocketChannel sChannel = ssChannel.accept();
		
		while(sChannel.read(buffer)!=-1){
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		
		buffer.put("接收到了".getBytes());
		buffer.flip();
		sChannel.write(buffer);
		buffer.clear();
		
		ssChannel.close();
		outChannel.close();
		sChannel.close();
	}
	
}
