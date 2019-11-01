package com.xmz.core.servlet.base;

import com.xmz.core.enums.RequestMethod;
import com.xmz.core.exception.ServerErrorException;
import com.xmz.core.exception.ServletNotFoundException;
import com.xmz.core.exception.base.ServletException;
import com.xmz.core.exception.handler.ExceptionHandler;
import com.xmz.core.request.Request;
import com.xmz.core.resource.ResourceHandler;
import com.xmz.core.response.Response;
import java.io.IOException;
import java.net.Socket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.servlet
 * @class: RequestHandler.java
 * @description: Servlet运行容器
 * @Date 2019/10/28 10:35
 */
@Getter
@Setter
@AllArgsConstructor
public class RequestHandler implements Runnable{
		private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);


		private Socket client;
		private Request request;
		private Response response;

		private HTTPServlet servlet;
		private ExceptionHandler exceptionHandler;
		private ResourceHandler resourceHandler;



		@Override
		public void run() {

				//为了让request能找得到response，以设置cookie
				request.setRequestHandler(this);

				try {
						if (request.getMethod()== RequestMethod.GET &&(request.getUrl().contains(".") || request.getUrl().equals("/"))){
								logger.info("静态资源:{} ",request.getUrl());
								request.setUrl("/index.html");
								//首页
								if (request.getUrl().equals("/")) {
										request.setUrl("/index.html");
										resourceHandler.handle(request, response, client);
								} else {
										resourceHandler.handle(request, response, client);
								}
						}else {
								if (servlet == null) {
										throw new ServletNotFoundException();
								}
								//处理动态资源，交由某个Servlet执行
								//Servlet是单例多线程
								//Servlet在RequestHandler中执行
								servlet.service(request, response);
								response.write();
						}
				} catch (ServletException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}catch (Exception e){
						exceptionHandler.handle(new ServerErrorException(), response, client);
				}


		}
}
