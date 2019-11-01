package com.xmz.core.exception;


import com.xmz.core.enums.HTTPStatus;
import com.xmz.core.exception.base.ServletException;

/**
 * Created by SinjinSong on 2017/7/21.
 */
public class ServerErrorException extends ServletException {
    private static final HTTPStatus status = HTTPStatus.INTERNAL_SERVER_ERROR;
    public ServerErrorException() {
        super(status);
    }
}
