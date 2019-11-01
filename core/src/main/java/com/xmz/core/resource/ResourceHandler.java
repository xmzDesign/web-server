package com.xmz.core.resource;

import com.xmz.core.constant.CharsetProperties;
import com.xmz.core.enums.HTTPStatus;
import com.xmz.core.exception.RequestParseException;
import com.xmz.core.exception.ResourceNotFoundException;
import com.xmz.core.exception.base.ServletException;
import com.xmz.core.exception.handler.ExceptionHandler;
import com.xmz.core.request.Request;
import com.xmz.core.response.Response;
import com.xmz.core.template.TemplateResolver;
import com.xmz.core.util.IOUtil;
import com.xmz.core.util.MimeTypeUtil;
import java.io.IOException;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.resource
 * @class: ResourceHandler.java
 * @description:
 * @Date 2019/10/28 11:31
 */
public class ResourceHandler {
		private static final Logger log = LoggerFactory.getLogger(ResourceHandler.class);


		private ExceptionHandler exceptionHandler;

		public ResourceHandler(ExceptionHandler exceptionHandler) {
				this.exceptionHandler = exceptionHandler;
		}

		public void handle(Request request, Response response, Socket client) {
				String url = request.getUrl();
				try {
						if (ResourceHandler.class.getResource(url) == null) {
								log.info("找不到该资源:{}", url);
								throw new ResourceNotFoundException();
						}
						byte[] body = IOUtil.getBytesFromFile(url);
						if (url.endsWith(".html")) {
								body = TemplateResolver
										.resolve(new String(body, CharsetProperties.UTF_8_CHARSET), request)
										.getBytes(CharsetProperties.UTF_8_CHARSET);
						}
						response.header(HTTPStatus.OK, MimeTypeUtil.getTypes(url))
								.body(body)
								.write();
				} catch (IOException e) {
						exceptionHandler.handle(new RequestParseException(), response, client);
				} catch (ServletException e) {
						exceptionHandler.handle(e, response, client);
				}
		}
}
