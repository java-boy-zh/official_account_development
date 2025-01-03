package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.handler.action.ActionFactory;
import com.java.boy.zh.wx.handler.action.ActionHandler;
import com.java.boy.zh.wx.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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

    @Resource
    private ActionFactory actionFactory;

    @PostMapping(value = "/", produces = "application/xml;charset=UTF-8")
    public String callBack(@RequestBody String requestBody) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);

        // 处理用户不同操作
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        log.info("msgType:{},event:{}", msgType, event);

        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if (!StringUtils.isEmpty(event)) {
            sb.append(".");
            sb.append(event);
        }
        String msgTypeKey = sb.toString();

        ActionHandler handler = actionFactory.getHandler(msgTypeKey);
        if (Objects.isNull(handler)) return null;
        String message = handler.getMessage(msgMap);

        log.info("返回消息：message：{}", message);
        return message;
    }

}
