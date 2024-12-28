package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.enums.ActionType;
import com.java.boy.zh.wx.utils.WeChatResultUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 关注公众号消息处理器
 * @Version V1.0
 */
@Component
public class SubscribeActionHandler implements ActionHandler {
    @Override
    public ActionType getMessageType() {
        return ActionType.SUBSCRIBE;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {

        // 拿到信息
        String toUserId = messageMap.get("FromUserName");
        String fromUserId = messageMap.get("ToUserName");

        String content = "感谢您的关注！";
        String response = WeChatResultUtil.buildTextResponse(toUserId, fromUserId, content);


        return response;
    }
}