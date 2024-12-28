package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.enums.ActionOrMessageType;
import com.java.boy.zh.wx.utils.ResultUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 文字消息处理器
 * @Version V1.0
 */
@Component
public class TextMessageHandler implements ActionHandler {
    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.TEXT;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {
        String toUserId = messageMap.get("FromUserName");
        String fromUserId = messageMap.get("ToUserName");

        // 笑话
//        String joke = JokeUtil.getJoke();
//        String response = WeChatResultUtil.buildTextResponse(toUserId, fromUserId, joke);
        String response = ResultUtil.buildTextResponse(toUserId, fromUserId, "22222222222");

        return response;
    }

}