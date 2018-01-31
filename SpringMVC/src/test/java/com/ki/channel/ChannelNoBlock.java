package com.ki.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import com.ki.test.base.BaseDaoBeanSupport;

public class ChannelNoBlock extends BaseDaoBeanSupport{


	@Test
	public void client() throws IOException{
		
		SocketChannel socketChannel = SocketChannel.open();
		ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
		ByteBuffer bufferRead = ByteBuffer.allocate(1024);
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(8899));
		
		if(socketChannel.finishConnect()){
			System.out.println(this.hashCode()+": 已连接到服务器。。。");
			
			final Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_READ, bufferRead);
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					try{
						while(selector.select()>0){
							Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
							SelectionKey selectionKey = null;
							while(selectionKeys.hasNext()){
								selectionKey = selectionKeys.next();
								// read
								if(selectionKey.isReadable()){
									SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
									ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
									int readNum = -1;
									while((readNum=socketChannel.read(buffer))>0){
										buffer.flip();
										System.out.println(new String(buffer.array(), 0, readNum));
										buffer.clear();
									}
								}
								selectionKeys.remove();
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}).start();
			
			Scanner scanner = new Scanner(System.in);
			String sendContent = scanner.nextLine();
			while(!"Bye".equals(sendContent)){
				bufferWrite.put(sendContent.getBytes());
				bufferWrite.flip();
				socketChannel.write(bufferWrite);
				bufferWrite.clear();
				sendContent = scanner.nextLine();
			}
			
			
			
			
			socketChannel.close();
			scanner.close();
			
			System.out.println(this.hashCode()+": 退出连接");
		}
		
	}

	@Test
	public void server() throws IOException {
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		Selector selector = Selector.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(8899));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, ByteBuffer.allocate(1024));
		
		while(selector.select()>0){
			Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
			SelectionKey selectionKey = null;
			while(selectionKeys.hasNext()){
				selectionKey = selectionKeys.next();
				
				// accept
				if(selectionKey.isAcceptable()){
					System.out.println("isAcceptable");
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE, ByteBuffer.allocate(1024));
				}
				
				// read
				if(selectionKey.isReadable()){
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
					int readNum = -1;
					while((readNum=socketChannel.read(buffer))>0){
						buffer.flip();
						System.out.println(new String(buffer.array(), 0, readNum));
						buffer.clear();
					}
				}
				
				// write
				if(selectionKey.isWritable()){
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
					Scanner scanner = new Scanner(System.in);
					buffer.put(scanner.nextLine().getBytes());
					buffer.flip();
					socketChannel.write(buffer);
					buffer.clear();
				}
				selectionKeys.remove();
			}
		}
	}
	
}
