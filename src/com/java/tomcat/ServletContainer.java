package com.java.tomcat;

import java.util.HashMap;
import java.util.Map;

import com.java.tomcat.model.Servlet;
import com.java.tomcat.model.ServletMapping;
import com.java.tomcat.servlet.HttpServlet;
import com.java.tomcat.util.XMLUtil;

/***
 * 
 * Title:ServletContainer
 * Description: 模拟servlet容器
 * Company: 
 * @author MrWang
 * @date 2018年3月16日 上午10:51:22
 */
public class ServletContainer {

	private static Map<String, Object> servletMaps = new HashMap<>();
	private static Map<String, Object> servletMappingMaps = new HashMap<>();
	private static Map<String, HttpServlet> servletContainer = new HashMap<>();

	static {
		try {
			Map<Integer, Map<String, Object>> maps = XMLUtil.parseWebXML("web.xml");
			if (maps != null && 2 == maps.size()) {
				servletMaps = maps.get(0);
				servletMappingMaps = maps.get(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static HttpServlet getHttpServlet(String path) {

		if (path == null || "".equals(path) || "/".equals(path)) {
			path = "/index";
		}
		if (servletContainer.containsKey(path)) {
			return servletContainer.get(path);
		}

		if (!servletMappingMaps.containsKey(path)) {
			return null;
		}

		ServletMapping servletMapping = (ServletMapping) servletMappingMaps.get(path);
		String name = servletMapping.getName();
		
		if (!servletMaps.containsKey(name)) {
			return null;
		}
		Servlet servlet = (Servlet) servletMaps.get(name);
		String clazz = servlet.getClazz();
		
		if(clazz==null||"".equals(clazz)) {
			return null;
		}
		HttpServlet httpServlet=null;
		try {
			httpServlet = (HttpServlet) Class.forName(clazz).newInstance();
			servletContainer.put(path, httpServlet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return httpServlet;
	}

}
