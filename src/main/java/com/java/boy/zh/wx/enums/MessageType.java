package com.java.boy.zh.wx.enums;

import java.util.Arrays;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信公众号消息类型
 * @Version V1.0
 */
public enum MessageType {
    TEXT("text", "文本消息"),
    IMAGE("image", "图片消息"),
    VOICE("voice", "语音消息"),
    VIDEO("video", "视频消息"),
    SHORTVIDEO("shortvideo", "小视频消息"),
    LOCATION("location", "地理位置消息"),
    LINK("link", "链接消息");

    private final String type;
    private final String description;

    MessageType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static MessageType getMessageType(String type) {
        return Arrays.stream(values())
                .filter(messageType -> messageType.getType().equals(type))
                .findFirst()
                .orElse(null);
    }


}
