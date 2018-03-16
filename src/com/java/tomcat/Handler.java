package com.java.tomcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.java.tomcat.servlet.HttpServlet;

/****
 * 
 * Title:Handler
 * Description: 初始化请求
 * Company: 
 * @author MrWang
 * @date 2018年3月16日 上午10:52:57
 */
public class Handler implements Runnable {

	private Socket socket;
	private PrintWriter writer;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			writer.println("HTTP/1.1 200 OK");
			writer.println("Content-Type: text/html;charset=UTF-8");
			writer.println();

			Request request = new Request();
			Response response = new Response(writer);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			while (true) {
				String msg = reader.readLine();
				if (msg == null || "".equals(msg.trim())) {
					break;
				}

				String[] msgs = msg.split(" ");
				if (3 == msgs.length && "HTTP/1.1".equalsIgnoreCase(msgs[2])) {
					request.setMethod(msgs[0]);
					request.setPath(msgs[1]);

					String[] attributesPath = msgs[1].split("\\?");
					request.setPath(attributesPath[0]);
					if (attributesPath.length > 1) {
						Map<String, String> attributeMap = new HashMap<>();
						String[] params = attributesPath[1].split("&");
						for (String string : params) {
							attributeMap.put(string.split("=")[0], string.split("=")[1]);
						}
						request.setAttribute(attributeMap);
					}
					break;
				}
			}
			if (request.getPath().endsWith(".ico")) {
				return;
			}

			HttpServlet httpServlet = request.initServlet();
			dispatcher(httpServlet, request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void dispatcher(HttpServlet httpServlet, Request request, Response response) {
		// TODO Auto-generated method stub
		try {
			if (httpServlet == null) {
				response.writer("<h1>404 Not Found</h1>");
				return;
			}

			if ("GET".equalsIgnoreCase(request.getMethod())) {
				httpServlet.doGet(request, response);
			} else if ("POST".equalsIgnoreCase(request.getMethod())) {
				httpServlet.doPost(request, response);
			}
		} catch (Exception e) {
			response.writer("<h1>500 Internal Server Error</h1>\r\n" + Arrays.toString(e.getStackTrace()));
			e.printStackTrace();
		}

	}

}
