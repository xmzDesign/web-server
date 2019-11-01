package com.xmz.core.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.model
 * @class: HTTPSession.java
 * @description:
 * @Date 2019/10/30 15:57
 */
public class HTTPSession {
		private String id;
		private Map<String, Object> attributes;

		public HTTPSession(String id){
				this.id = id;
				attributes = new ConcurrentHashMap();
		}

		public Object getAttribute(String key) {
				return attributes.get(key);
		}

		public void setAttribute(String key, Object value) {
				attributes.put(key, value);
		}
		public String getId(){
				return id;
		}

}
