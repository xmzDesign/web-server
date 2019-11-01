package com.xmz.core.servlet.context;

import com.xmz.core.model.Cookie;
import com.xmz.core.model.HTTPSession;
import com.xmz.core.response.Response;
import com.xmz.core.servlet.base.HTTPServlet;
import com.xmz.core.util.UUIDUtil;
import com.xmz.core.util.XMLUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.servlet.context
 * @class: ServletContext.java
 * @description:
 * @Date 2019/10/28 10:26
 */
@Data
public class ServletContext {
		//别名->类名
		//一个Servlet类只能有一个Servlet别名，一个Servlet别名只能对应一个Servlet类
		private Map<String, HTTPServlet> servlet;
		//一个Servlet可以对应多个URL，一个URL只能对应一个Servlet
		//URL->Servlet别名
		private Map<String, String> mapping;

		private Map<String, Object> attributes;
		private Map<String, HTTPSession> sessions;

		public ServletContext() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
				init();
		}

		/**
		 * 解析web.xml
		 */
		private void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
				this.servlet = new HashMap();
				this.mapping = new HashMap();
				this.attributes = new ConcurrentHashMap();
				this.sessions = new ConcurrentHashMap();
				Document doc = XMLUtil.getDocument(ServletContext.class.getResourceAsStream("/web.xml"));
				Element root = doc.getRootElement();
				List<Element> servlets = root.elements("servlet");
				for (Element servlet : servlets) {
						String key = servlet.element("servlet-name").getText();
						String value = servlet.element("servlet-class").getText();
						HTTPServlet httpServlet = null;
						httpServlet = (HTTPServlet) Class.forName(value).newInstance();
						this.servlet.put(key, httpServlet);
				}
				List<Element> mappings = root.elements("servlet-mapping");
				for (Element mapping : mappings) {
						String key = mapping.element("url-pattern").getText();
						String value = mapping.element("servlet-name").getText();
						this.mapping.put(key, value);
				}
		}

		public HTTPServlet dispatch(String url) {
				return servlet.get(mapping.get(url));
		}


		public HTTPSession getSession(String JSESSIONID) {
				return sessions.get(JSESSIONID);
		}

		public HTTPSession createSession(Response response) {
				HTTPSession session = new HTTPSession(UUIDUtil.uuid());
				sessions.put(session.getId(), session);
				response.addCookie(new Cookie("JSESSIONID", session.getId()));
				return session;
		}

		public Object getAttribute(String key) {
				return attributes.get(key);
		}

		public void setAttribute(String key, Object value) {
				attributes.put(key, value);
		}
}
