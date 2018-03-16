package com.java.tomcat.model;
/****
 * 
 * Title:ServletMapping
 * Description: ServletMapping实体
 * Company: 
 * @author MrWang
 * @date 2018年3月16日 下午2:17:27
 */
public class ServletMapping {

	private String name;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
