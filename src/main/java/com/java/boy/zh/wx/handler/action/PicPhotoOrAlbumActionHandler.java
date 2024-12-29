package com.java.boy.zh.wx.handler.action;

import com.java.boy.zh.wx.constant.MessageConstant;
import com.java.boy.zh.wx.enums.ActionOrMessageType;
import com.java.boy.zh.wx.utils.ResultUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 自定义菜单事件处理器
 * @Version V1.0
 */
@Component
public class PicPhotoOrAlbumActionHandler implements ActionHandler {
    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.PIC_PHOTO_OR_ALBUM;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {

        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(MessageConstant.TOUSERNAME, messageMap.get(MessageConstant.FROMUSERNAME));
        msgMap.put(MessageConstant.FROMUSERNAME, messageMap.get(MessageConstant.TOUSERNAME));
        msgMap.put(MessageConstant.CONTENT, String.format("发送图片菜单点击 %s！", new Date().getTime()));

        return ResultUtil.buildTextResponse(msgMap);
    }
}