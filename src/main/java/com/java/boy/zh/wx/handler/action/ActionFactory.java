package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.enums.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 16:38
 * @Description 操作的工厂
 * @Version V1.0
 */
@Slf4j
@Component
public class ActionFactory {
    private static final Map<String, ActionHandler> handlerMap = new HashMap<>();

    @Autowired
    public ActionFactory(List<ActionHandler> handlers) {
        for (ActionHandler handler : handlers) {
            ActionType actionType = handler.getMessageType();
            if (actionType != null) {
                handlerMap.put(actionType.getAction(), handler);
            }
        }
    }

    public ActionHandler getHandler(String msgType) {
        return handlerMap.get(msgType);
    }
}
