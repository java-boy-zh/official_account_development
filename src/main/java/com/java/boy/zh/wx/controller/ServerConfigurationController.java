package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 12:00
 * @Description 微信服务器配置controller
 * @Version V1.0
 */
@RestController
@Slf4j
public class ServerConfigurationController {

    @Value("${wx.token}")
    private String token;

    @GetMapping("/")
    public String callBack(String signature,
                           String timestamp,
                           String nonce,
                           String echostr) {


        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }

}
