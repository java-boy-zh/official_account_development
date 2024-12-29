package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.entity.qr.QrCodeResponse;
import com.java.boy.zh.wx.service.QrCodeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信二维码相关接口控制器
 */
@RestController
@RequestMapping("/wx/qrcode")
public class QrCodeController {

    @Resource
    private QrCodeService qrCodeService;

    /**
     * 创建临时二维码
     *
     * @param scene_id       场景值ID
     * @param expire_seconds 过期时间(秒),默认30天
     * @return 二维码信息
     */
    @PostMapping("/temp")
    public QrCodeResponse createTempQrCode(
            @RequestParam Integer scene_id,
            @RequestParam(defaultValue = "2592000") Integer expire_seconds) {
        return qrCodeService.createTempQrCode(scene_id, expire_seconds);
    }

    /**
     * 创建永久二维码
     *
     * @param scene_id 场景值ID
     * @return 二维码信息
     */
    @PostMapping("/permanent")
    public QrCodeResponse createPermanentQrCode(@RequestParam Integer scene_id) {
        return qrCodeService.createPermanentQrCode(scene_id);
    }

    /**
     * 获取二维码图片
     *
     * @param ticket 二维码ticket
     * @return 二维码图片
     */
    @GetMapping("/image")
    public ResponseEntity<byte[]> getQrCodeImage(@RequestParam String ticket) {
        byte[] qrCodeBytes = qrCodeService.getQrCodeImage(ticket);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
    }
} 