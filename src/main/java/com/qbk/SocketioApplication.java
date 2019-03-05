package com.qbk;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 配置
 */
@SpringBootApplication
public class SocketioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketioApplication.class, args);
	}

	/**
	 * 配置 SocketIO Server
	 * @return
	 */
	@Bean
	public SocketIOServer socketIOServer() {
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

		String os = System.getProperty("os.name");
		//在本地window环境测试时用localhost
		if(os.toLowerCase().startsWith("win")){
			System.out.println("this is  windows");
			config.setHostname("localhost");
		} else {
			//部署到你的远程服务器正式发布环境时用服务器公网ip
			config.setHostname("123.123.111.222");
		}
		config.setPort(9092);

		/*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
			@Override
			public boolean isAuthorized(HandshakeData data) {
				//http://localhost:8081?username=test&password=test
				//例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
				// String username = data.getSingleUrlParam("username");
				// String password = data.getSingleUrlParam("password");
				return true;
			}
		});*/


		final SocketIOServer server = new SocketIOServer(config);
		return server;
	}

	/**
	 * 注解扫描
	 * @param socketServer
	 * @return
	 */
	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}


}
