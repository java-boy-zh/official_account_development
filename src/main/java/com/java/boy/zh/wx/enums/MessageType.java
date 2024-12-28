package com.java.boy.zh.wx.enums;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信公众号消息类型
 * @Version V1.0
 */
public enum MessageType {
    TEXT("text"),           // 文本消息
    IMAGE("image"),         // 图片消息
    VOICE("voice"),         // 语音消息
    VIDEO("video"),         // 视频消息
    SHORTVIDEO("shortvideo"), // 小视频消息
    LOCATION("location"),   // 地理位置消息
    LINK("link");          // 链接消息
    private final String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
