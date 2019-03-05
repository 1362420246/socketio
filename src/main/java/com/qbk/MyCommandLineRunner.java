package com.qbk;


import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动
 */
@Component
@Order(value=1)
public class MyCommandLineRunner implements CommandLineRunner {

    /**
     * 注入 SocketIO Server
     */
    private final SocketIOServer server;

    @Autowired
    public MyCommandLineRunner(SocketIOServer server) {
        this.server = server;
    }


    @Override
    public void run(String... args) throws Exception {
        //开启服务
        server.start();
        System.out.println("socket.io启动成功！");
    }


}
