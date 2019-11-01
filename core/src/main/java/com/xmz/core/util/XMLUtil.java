package com.xmz.core.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.util
 * @class: XMLUtil.java
 * @description:
 * @Date 2019/10/30 16:00
 */
public class XMLUtil {
		public static Document getDocument(InputStream in) {
				try {
						SAXReader reader = new SAXReader();
						return reader.read(in);
				} catch (DocumentException e) {
						e.printStackTrace();
				}
				return null;
		}

}
