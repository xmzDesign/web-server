package com.xmz.sample.servlet;


import com.xmz.core.exception.base.ServletException;
import com.xmz.core.request.Request;
import com.xmz.core.response.Response;
import com.xmz.core.servlet.base.HTTPServlet;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by SinjinSong on 2017/7/21.
 */
@Slf4j
public class UserServlet extends HTTPServlet {
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            log.info("已经登录");
            request.getRequestDispatcher("/views/user.html").forward(request, response);
        } else {
            log.info("尚未登录，跳转至login");
            //必须使用从浏览器角度看的路径，凡是静态资源，前面都没有/static or /views
            //但是forward是从服务器角度看的路径，是真实的、相对的路径
            response.sendRedirect("/views/login.html");
        }
    }
}
