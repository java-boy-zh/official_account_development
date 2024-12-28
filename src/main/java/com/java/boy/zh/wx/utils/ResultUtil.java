package com.java.boy.zh.wx.utils;

import com.java.boy.zh.wx.constant.MessageConstant;
import com.java.boy.zh.wx.entity.NewsMessage;
import com.java.boy.zh.wx.entity.TextMessage;
import com.java.boy.zh.wx.enums.ActionOrMessageType;

import java.util.LinkedList;
import java.util.Map;


public class ResultUtil {

    /**
     * 文字消息
     */
    public static String buildTextResponse(Map<String, String> msgMap) {
        TextMessage response = new TextMessage();
        response.setToUserName(msgMap.get(MessageConstant.TOUSERNAME));
        response.setFromUserName(msgMap.get(MessageConstant.FROMUSERNAME));
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.TEXT.getAction());
        response.setContent(msgMap.get(MessageConstant.CONTENT));
        return MessageUtil.convertToXml(response);
    }


    /**
     * 图文消息
     */
    public static String buildNewsResponse(Map<String, String> msgMap) {
        NewsMessage response = new NewsMessage();
        response.setToUserName(msgMap.get(MessageConstant.TOUSERNAME));
        response.setFromUserName(msgMap.get(MessageConstant.FROMUSERNAME));
        response.setCreateTime(System.currentTimeMillis() / 1000);
        response.setMsgType(ActionOrMessageType.NEWS.getAction());
        response.setArticleCount(1);

        LinkedList<NewsMessage.ArticlesItem> articlesItems = new LinkedList<>();
        NewsMessage.ArticlesItem item = new NewsMessage.ArticlesItem();
        item.setTitle("王青玄微信公众号开发图文");
        item.setDescription("王青玄微信公众号开发图文测试描述");
        item.setPicUrl("http://mmbiz.qpic.cn/sz_mmbiz_jpg/5eH0lfZ9Wia4veTthSNjraaP97MKIp8LSVFIibe7dkTIyavFI2Q0v2RR2U0UFfo2NSjknQ2Cribfo9Seyaf6n77oA/0");
        item.setUrl("https://www.baidu.com/");

        articlesItems.add(item);
        response.setArticles(articlesItems);

        return MessageUtil.convertToXml(response);
    }

}
