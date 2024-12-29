package com.java.boy.zh.wx.service;


import com.java.boy.zh.wx.entity.qr.QrCodeResponse;

/**
 * 微信二维码相关服务接口
 */
public interface QrCodeService {

    /**
     * 创建临时二维码
     *
     * @param sceneId       场景值ID,32位非0整型
     * @param expireSeconds 二维码有效时间(秒),最大30天
     * @return 二维码信息
     */
    QrCodeResponse createTempQrCode(Integer sceneId, Integer expireSeconds);

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值ID,最大值为100000
     * @return 二维码信息
     */
    QrCodeResponse createPermanentQrCode(Integer sceneId);

    /**
     * 通过ticket获取二维码图片字节流
     *
     * @param ticket 二维码ticket
     * @return 图片字节数组
     */
    byte[] getQrCodeImage(String ticket);
} 