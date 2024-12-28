package com.java.boy.zh.wx.enums;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信公众号操作类型
 * @Version V1.0
 */
public enum ActionOrMessageType {
    SUBSCRIBE("event.subscribe", "关注公众号"),
    UNSUBSCRIBE("event.unsubscribe", "取消关注公众号"),
    TEXT("text", "文本消息"),
    IMAGE("image", "图片消息"),
    VOICE("voice", "语音消息"),
    VIDEO("video", "视频消息"),
    SHORTVIDEO("shortvideo", "小视频消息"),
    LOCATION("location", "地理位置消息"),
    LINK("link", "链接消息"),
    NEWS("news", "图文消息")
    ;

    private final String action;
    private final String description;

    ActionOrMessageType(String action, String description) {
        this.action = action;
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public static ActionOrMessageType getWeChatActionType(String action) {
        for (ActionOrMessageType actionOrMessageType : ActionOrMessageType.values()) {
            if (actionOrMessageType.getAction().equals(action)) {
                return actionOrMessageType;
            }
        }
        return null; // 如果没有找到匹配的类型，则返回null
    }
}