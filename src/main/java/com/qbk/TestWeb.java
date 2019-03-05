package com.qbk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
public class TestWeb {


    /**
     * 测试报警推送服务:主要应用一个方法pushArr
     */
    @GetMapping("/pushMessage")
    public void pushMessage(){
        MessageEventHandler.sendBuyLogEvent();
    }
}
