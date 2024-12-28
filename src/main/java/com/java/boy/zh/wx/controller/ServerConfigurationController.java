package com.java.boy.zh.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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


        // 1）将token、timestamp、nonce三个参数进行字典序排序
        List<String> stringList = Arrays.asList(token, timestamp, nonce);
        Collections.sort(stringList);

        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String s : stringList) {
            sb.append(s);
        }
        
        try {
            MessageDigest sha1 = MessageDigest.getInstance("sha1");
            byte[] temp = sha1.digest(sb.toString().getBytes());

            // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            StringBuilder tempHex = new StringBuilder();
            for (byte b : temp) {
                // 一个字节时8位 每4位表示一个16进制数
                tempHex.append(Integer.toHexString((b >> 4) & 15));
                tempHex.append(Integer.toHexString(b & 15));
            }

            if (tempHex.toString().equals(signature)) {
                return echostr;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
