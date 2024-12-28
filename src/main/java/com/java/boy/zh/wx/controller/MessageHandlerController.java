package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.utils.MessageUtil;
import com.java.boy.zh.wx.utils.WeChatResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信公众号消息controller
 * @Version V1.0
 */
@RestController
@Slf4j
public class MessageHandlerController {

    @PostMapping(value = "/", produces = "application/xml;charset=UTF-8")
    public String callBack(@RequestBody String requestBody) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> map = MessageUtil.parseXml(requestBody);
        // 拿到信息
        String toUserId = map.get("FromUserName");
        String fromUserId = map.get("ToUserName");

        String content = "欢迎关注！";
        String response = WeChatResultUtil.buildTextResponse(toUserId, fromUserId, content);
        log.info("返回的微信消息：response：{}", response);
        return response;
    }

}
