package com.xmz.core.request.dispatcher;

import com.xmz.core.exception.base.ServletException;
import com.xmz.core.request.Request;
import com.xmz.core.response.Response;
import java.io.IOException;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core.request.dispatcher
 * @class: RequestDispatcher.java
 * @description:
 * @Date 2019/10/30 16:36
 */
public interface RequestDispatcher {
		void forward(Request request, Response response)  throws ServletException, IOException;

}
