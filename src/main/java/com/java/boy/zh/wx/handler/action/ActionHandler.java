package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.enums.ActionType;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 16:25
 * @Description 消息处理器
 * @Version V1.0
 */
public interface ActionHandler {

    // 消息类型
    ActionType getMessageType();

    // 获取回复消息
    String getMessage(Map<String,String> messageMap);
}