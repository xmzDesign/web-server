package com.xmz.core.exception;


import com.xmz.core.enums.HTTPStatus;
import com.xmz.core.exception.base.ServletException;

/**
 * Created by SinjinSong on 2017/7/21.
 */
public class ServletNotFoundException extends ServletException {
    private static final HTTPStatus status = HTTPStatus.NOT_FOUND;
    public ServletNotFoundException() {
        super(status);
    }
}
