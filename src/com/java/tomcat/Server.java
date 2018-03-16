package com.java.tomcat;

import java.net.ServerSocket;
import java.net.Socket;

import com.java.tomcat.threadpool.ExecutorProcessPool;

/****
 * 
 * Title:Server
 * Description: 模拟服务启动类
 * Company:
 * @author MrWang
 * @date 2018年3月16日 上午10:12:08
 */
public class Server {

	private static ServerSocket serverSocket;
	private static int port = 8080;

	private static ExecutorProcessPool processPool = ExecutorProcessPool.getInstance();

	public void start() {
		try {
			serverSocket = new ServerSocket(port);

			System.out.println("Starting hander in [" + port + "]");
			Socket socket = null;
			while (true) {
				socket = serverSocket.accept();
				processPool.execute(new Handler(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server().start();
	}

}
