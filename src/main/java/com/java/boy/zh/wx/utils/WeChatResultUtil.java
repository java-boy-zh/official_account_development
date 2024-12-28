package com.java.boy.zh.wx.utils;

import com.java.boy.zh.wx.entity.WeChatResponse;
import com.java.boy.zh.wx.enums.ActionOrMessageType;

public class WeChatResultUtil {

    public static String buildTextResponse(String toUser, String fromUser, String content) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.TEXT.getAction());
        response.setContent(content);
        return MessageUtil.convertToXml(response);
    }

    public static String buildImageResponse(String toUser, String fromUser, String mediaId) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.IMAGE.getAction());
        response.setMediaId(mediaId);
        return MessageUtil.convertToXml(response);
    }

    public static String buildVoiceResponse(String toUser, String fromUser, String mediaId) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.VOICE.getAction());
        response.setMediaId(mediaId);
        return MessageUtil.convertToXml(response);
    }

    public static String buildVideoResponse(String toUser, String fromUser, String mediaId,
                                            String title, String description) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.VIDEO.getAction());
        response.setMediaId(mediaId);
        response.setTitle(title);
        response.setDescription(description);
        return MessageUtil.convertToXml(response);
    }

    public static String buildShortVideoResponse(String toUser, String fromUser, String mediaId) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.SHORTVIDEO.getAction());
        response.setMediaId(mediaId);
        return MessageUtil.convertToXml(response);
    }

    public static String buildLocationResponse(String toUser, String fromUser,
                                               String locationX, String locationY,
                                               String scale, String label) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.LOCATION.getAction());
        response.setLocationX(locationX);
        response.setLocationY(locationY);
        response.setScale(scale);
        response.setLabel(label);
        return MessageUtil.convertToXml(response);
    }

    public static String buildLinkResponse(String toUser, String fromUser,
                                           String title, String description, String url) {
        WeChatResponse response = new WeChatResponse();
        response.setToUserName(toUser);
        response.setFromUserName(fromUser);
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.LINK.getAction());
        response.setTitle(title);
        response.setDescription(description);
        response.setUrl(url);
        return MessageUtil.convertToXml(response);
    }
}
