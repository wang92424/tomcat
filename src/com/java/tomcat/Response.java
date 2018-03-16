package com.java.tomcat;

import java.io.PrintWriter;

/****
 * 
 * Title:Response
 * Description: 模拟返回请求类
 * Company: 
 * @author MrWang
 * @date 2018年3月16日 上午11:00:05
 */
public class Response {

	private PrintWriter writer;
	
	public Response(PrintWriter writer) {
		this.writer = writer;
	}
	
	public void writer(String msg) {
		writer.write(msg);
		writer.flush();
	}
	
}
