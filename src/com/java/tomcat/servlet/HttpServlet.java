package com.java.tomcat.servlet;

import java.io.IOException;

import com.java.tomcat.Request;
import com.java.tomcat.Response;

/***
 * 
 * Title:HttpServlet
 * Description: ģ��servlet������
 * Company: 
 * @author MrWang
 * @date 2018��3��16�� ����10:55:16
 */
public class HttpServlet {

	public void doGet(Request request, Response response) throws IOException {

	};

	public void doPost(Request request, Response response) throws IOException {

	};

	public void service(Request request, Response response) throws IOException {
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			this.doGet(request, response);
		} else {
			this.doPost(request, response);
		}
	}
}
