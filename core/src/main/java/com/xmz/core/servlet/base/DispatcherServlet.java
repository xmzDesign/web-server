package com.xmz.core.servlet.base;

import com.xmz.core.WebApplication;
import com.xmz.core.exception.RequestInvalidException;
import com.xmz.core.exception.RequestParseException;
import com.xmz.core.servlet.context.ServletContext;
import com.xmz.core.exception.handler.ExceptionHandler;
import com.xmz.core.request.Request;
import com.xmz.core.resource.ResourceHandler;
import com.xmz.core.response.Response;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.servlet
 * @class: DispatcherServlet.java
 * @description:
 * @Date 2019/10/28 10:03
 */
public class DispatcherServlet {

		private ServletContext servletContext;

		private ThreadPoolExecutor pool;

		private ResourceHandler resourceHandler;

		private ExceptionHandler exceptionHandler;

		public DispatcherServlet() {
				this.servletContext = WebApplication.getServletContext();
				this.exceptionHandler = new ExceptionHandler();
				this.resourceHandler = new ResourceHandler(exceptionHandler);
				this.pool = new ThreadPoolExecutor(100, 200, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
		}

		public void doDispatch(Socket client) throws IOException {
				Request request = null;
				Response response = null;

				try {
						request = new Request(client.getInputStream());
						response = new Response(client.getOutputStream());
						request.setServletContext(servletContext);
						pool.execute(new RequestHandler(client, request, response, servletContext.dispatch(request.getUrl()), exceptionHandler, resourceHandler));
				} catch (IOException e) {
						e.printStackTrace();
				} catch (RequestParseException e) {
						e.printStackTrace();
				} catch (RequestInvalidException e) {
						e.printStackTrace();
				}


		}

		public void shutdown() {
				pool.shutdown();
		}
}
