package com.java.boy.zh.wx.service.impl;

import com.java.boy.zh.wx.entity.qr.QrCodeRequest;
import com.java.boy.zh.wx.entity.qr.QrCodeResponse;
import com.java.boy.zh.wx.service.QrCodeService;
import com.java.boy.zh.wx.utils.HttpUtil;
import com.java.boy.zh.wx.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信二维码服务实现类
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {

    /**
     * 创建二维码的微信接口URL
     */
    private static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    /**
     * 获取二维码图片的微信接口URL
     */
    private static final String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    @Override
    public QrCodeResponse createTempQrCode(Integer scene_id, Integer expire_seconds) {
        // 构建临时二维码请求参数
        QrCodeRequest request = new QrCodeRequest();
        request.setExpir_seconds(expire_seconds);
        request.setAction_name("QR_SCENE");

        QrCodeRequest.ActionInfo actionInfo = new QrCodeRequest.ActionInfo();
        QrCodeRequest.Scene scene = new QrCodeRequest.Scene();
        scene.setScene_id(scene_id);
        actionInfo.setScene(scene);
        request.setAction_info(actionInfo);

        // 发送请求创建二维码
        String url = CREATE_QRCODE_URL + TokenUtil.getAccessToken();
        return HttpUtil.postJson(url, request, QrCodeResponse.class);
    }

    @Override
    public QrCodeResponse createPermanentQrCode(Integer sceneId) {
        // 构建永久二维码请求参数
        QrCodeRequest request = new QrCodeRequest();
        request.setAction_name("QR_LIMIT_SCENE");

        QrCodeRequest.ActionInfo actionInfo = new QrCodeRequest.ActionInfo();
        QrCodeRequest.Scene scene = new QrCodeRequest.Scene();
        scene.setScene_id(sceneId);
        actionInfo.setScene(scene);
        request.setAction_info(actionInfo);

        // 发送请求创建二维码
        String url = CREATE_QRCODE_URL + TokenUtil.getAccessToken();
        return HttpUtil.postJson(url, request, QrCodeResponse.class);
    }

    @Override
    public byte[] getQrCodeImage(String ticket) {
        // URL编码ticket
        String encodedTicket = null;
        try {
            encodedTicket = URLEncoder.encode(ticket, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String url = GET_QRCODE_URL + encodedTicket;
        // 获取二维码图片字节流
        return HttpUtil.getBytes(url);
    }
} 