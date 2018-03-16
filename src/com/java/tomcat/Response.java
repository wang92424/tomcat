package com.java.tomcat;

import java.io.PrintWriter;

/****
 * 
 * Title:Response
 * Description: ģ�ⷵ��������
 * Company: 
 * @author MrWang
 * @date 2018��3��16�� ����11:00:05
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
