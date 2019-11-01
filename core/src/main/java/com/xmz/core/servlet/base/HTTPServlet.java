package com.xmz.core.servlet.base;

import com.xmz.core.enums.RequestMethod;
import com.xmz.core.exception.base.ServletException;
import com.xmz.core.request.Request;
import com.xmz.core.response.Response;
import java.io.IOException;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.servlet
 * @class: HTTPServlet.java
 * @description:
 * @Date 2019/10/28 11:41
 */
public abstract class HTTPServlet {
		public void service(Request request, Response response) throws ServletException, IOException {
				if (request.getMethod() == RequestMethod.GET) {
						doGet(request, response);
				} else if (request.getMethod() == RequestMethod.POST) {
						doPost(request, response);
				} else if (request.getMethod() == RequestMethod.PUT) {
						doPut(request, response);
				} else if (request.getMethod() == RequestMethod.DELETE) {
						doDelete(request, response);
				}
		}

		public void doGet(Request request, Response response) throws ServletException, IOException {
		}

		public void doPost(Request request, Response response) throws ServletException, IOException {
		}

		public void doPut(Request request, Response response) throws ServletException, IOException {
		}

		public void doDelete(Request request, Response response) throws ServletException, IOException {
		}

}
