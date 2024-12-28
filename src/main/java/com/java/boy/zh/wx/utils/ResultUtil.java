package com.java.boy.zh.wx.utils;

import com.java.boy.zh.wx.entity.TextMessage;
import com.java.boy.zh.wx.enums.ActionOrMessageType;

public class ResultUtil {

    /**
     * 文字消息
     */
    public static String buildTextResponse(String toUser, String fromUser, String content) {
        TextMessage response = new TextMessage();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.TEXT.getAction());
        response.setContent(content);
        return MessageUtil.convertToXml(response);
    }

}
