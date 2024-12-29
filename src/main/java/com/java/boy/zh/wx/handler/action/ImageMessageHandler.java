package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.constant.MessageConstant;
import com.java.boy.zh.wx.enums.ActionOrMessageType;
import com.java.boy.zh.wx.utils.BaiduOCRUtil;
import com.java.boy.zh.wx.utils.ResultUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 图片消息处理器
 * @Version V1.0
 */
@Component
public class ImageMessageHandler implements ActionHandler {
    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.IMAGE;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(MessageConstant.TOUSERNAME, messageMap.get(MessageConstant.FROMUSERNAME));
        msgMap.put(MessageConstant.FROMUSERNAME, messageMap.get(MessageConstant.TOUSERNAME));


        String content = messageMap.get(MessageConstant.PICURL);

        String ocr = BaiduOCRUtil.getOcr(content);
        msgMap.put(MessageConstant.CONTENT,ocr);

        return ResultUtil.buildNewsResponse(msgMap);
    }


}