package com.java.tomcat.servlet.impl;

import java.io.IOException;

import com.java.tomcat.Request;
import com.java.tomcat.Response;
import com.java.tomcat.servlet.HttpServlet;

public class TestServlet extends HttpServlet{

	@Override
	public void doGet(Request request, Response response) throws IOException {
		// TODO Auto-generated method stub

		response.writer("<h1>Servlet GET response!</h1><br>"+
		"name:"+request.getParameterl("name")+",pwd:"+request.getParameterl("pwd"));
	}
	
}
