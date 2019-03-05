package com.qbk;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * 长连接管理
 */
@Component
public class MessageEventHandler {

    @Autowired
    public SocketIOServer socketIOServer ;

    public static SocketIOServer socketIoServer;

    static ArrayList<UUID> listClient = new ArrayList<>();

    static final int limitSeconds = 60;

    @PostConstruct
    public void init( ) {
        socketIoServer = socketIOServer;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        listClient.add(client.getSessionId());
        System.out.println("客户端:" + client.getSessionId() + "已连接");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
    }


    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        System.out.println("发来消息：" + data.getMsgContent());
        socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", "back data");
    }


    //这里就是向客户端推消息了
    public static void sendBuyLogEvent() {
        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null){
                continue;
            }
            socketIoServer.getClient(clientId).sendEvent("enewbuy", "sss", 1);
        }

    }

}
