package com.java.tomcat;

import java.util.Map;

import com.java.tomcat.servlet.HttpServlet;

/***
 * 
 * Title:Request
 * Description: 模拟请求对象
 * Company: 
 * @author MrWang
 * @date 2018年3月16日 上午10:51:08
 */
public class Request {

	private String path;
	private String method;
	private String parameterl;
	private Map<String,String> attribute;
	
	public HttpServlet initServlet(){
		return ServletContainer.getHttpServlet(path);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParameterl() {
		return parameterl;
	}

	public String getParameterl(String name) {
		// TODO Auto-generated method stub
		String attr=null;
		if(attribute!=null) {
			attr = attribute.get(name);
		}
		return attr;
	}

	public void setParameterl(String parameterl) {
		this.parameterl = parameterl;
	}
	public Map<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	
}
