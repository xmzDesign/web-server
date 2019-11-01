package com.xmz.core.exception.handler;

import com.xmz.core.exception.RequestInvalidException;
import com.xmz.core.exception.base.ServletException;
import com.xmz.core.response.Response;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.exception.handler
 * @class: ExceptionHandler.java
 * @description:
 * @Date 2019/10/28 11:23
 */
public class ExceptionHandler {
		private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

		public void handle(ServletException e, Response response, Socket client) {
				if (e instanceof RequestInvalidException){
						logger.error("请求无法读取，丢弃");
				}else {
						logger.error("抛出异常",e);
				}

		}

}
