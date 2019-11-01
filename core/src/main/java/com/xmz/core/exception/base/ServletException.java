package com.xmz.core.exception.base;

import com.xmz.core.enums.HTTPStatus;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.exception.base
 * @class: ServletException.java
 * @description:
 * @Date 2019/10/28 11:25
 */
public class ServletException  extends Exception {
		private HTTPStatus status;
		public ServletException(HTTPStatus status){
				this.status = status;
		}

}
