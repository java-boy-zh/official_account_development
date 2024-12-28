package com.java.boy.zh.wx.enums;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信公众号操作类型
 * @Version V1.0
 */
public enum ActionType {
    SUBSCRIBE("event.subscribe", "关注公众号"),
    UNSUBSCRIBE("event.unsubscribe", "取消关注公众号")
    ;

    private final String action;
    private final String description;

    ActionType(String action, String description) {
        this.action = action;
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public static ActionType getWeChatActionType(String action) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getAction().equals(action)) {
                return actionType;
            }
        }
        return null; // 如果没有找到匹配的类型，则返回null
    }
}