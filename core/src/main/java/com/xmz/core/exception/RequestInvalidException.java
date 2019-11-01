package com.xmz.core.exception;


import com.xmz.core.enums.HTTPStatus;
import com.xmz.core.exception.base.ServletException;

/**
 * Created by SinjinSong on 2017/7/21.
 */
public class RequestInvalidException extends ServletException {
    private static final HTTPStatus status = HTTPStatus.BAD_REQUEST;
    public RequestInvalidException() {
        super(status);
    }
}
