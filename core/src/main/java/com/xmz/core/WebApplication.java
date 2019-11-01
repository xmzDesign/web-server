package com.xmz.core;

import com.xmz.core.servlet.context.ServletContext;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core
 * @class: WebApplication.java
 * @description:
 * @Date 2019/10/28 11:39
 */
public class WebApplication {
		private static ServletContext servletContext;

		public static void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
				servletContext = new ServletContext();

		}

		public static ServletContext getServletContext() {
				return servletContext;
		}

}
