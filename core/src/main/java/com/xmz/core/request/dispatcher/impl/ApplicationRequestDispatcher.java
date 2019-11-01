package com.xmz.core.request.dispatcher.impl;


import com.xmz.core.constant.CharsetProperties;
import com.xmz.core.enums.HTTPStatus;
import com.xmz.core.exception.ResourceNotFoundException;
import com.xmz.core.exception.base.ServletException;
import com.xmz.core.request.Request;
import com.xmz.core.request.dispatcher.RequestDispatcher;
import com.xmz.core.resource.ResourceHandler;
import com.xmz.core.response.Response;
import com.xmz.core.template.TemplateResolver;
import com.xmz.core.util.IOUtil;
import com.xmz.core.util.MimeTypeUtil;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by SinjinSong on 2017/7/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ApplicationRequestDispatcher implements RequestDispatcher {
    private String url;
    
    @Override
    public void forward(Request request, Response response) throws ServletException, IOException {
        if (ResourceHandler.class.getResource(url) == null) {
            throw new ResourceNotFoundException();
        }
        log.info("forward至 {} 页面",url);
        String body = TemplateResolver
						.resolve(new String(IOUtil.getBytesFromFile(url), CharsetProperties.UTF_8_CHARSET),request);
        response.header(HTTPStatus.OK, MimeTypeUtil.getTypes(url)).body(body.getBytes(CharsetProperties.UTF_8_CHARSET));
    }
}
