package com.xmz.core;

import com.xmz.core.servlet.base.DispatcherServlet;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.core
 * @class: Server.java
 * @description:
 * @Date 2019/10/28 09:57
 */
public class Server {
		private static final Logger logger = LoggerFactory.getLogger(Server.class);
		private static final int DEFAULT_PORT = 8080;

		private ServerSocket serverSocket;

		private DispatcherServlet dispatcherServlet;

		private Acceptor acceptor;

		public Server() {
				new Server(DEFAULT_PORT);
		}

		public Server(int port) {
				try {
						serverSocket=new ServerSocket(port);
						WebApplication.init();
						acceptor=new Acceptor();
						acceptor.start();
						dispatcherServlet=new DispatcherServlet();
						logger.info("服务器开始启动");
				} catch (Exception e) {
						e.printStackTrace();
						logger.error("服务器启动失败");
						close();
				}

		}

		public void close() {
				acceptor.shutdown();
				dispatcherServlet.shutdown();
		}


		private class Acceptor extends Thread{

				@Override
				public void run() {
						logger.info("开始监听");
						try {
								Socket accept = serverSocket.accept();
								logger.info("accept获得的数据：{}",accept);
								dispatcherServlet.doDispatch(accept);
						} catch (Exception e) {
								e.printStackTrace();
						}
				}

				@Override
				public void interrupt() {
						try {
								serverSocket.close();
						} catch (Exception e) {
								e.printStackTrace();
						} finally {
								super.interrupt();
						}
				}

				public void shutdown() {
						Thread.currentThread().interrupt();
				}
		}

}
