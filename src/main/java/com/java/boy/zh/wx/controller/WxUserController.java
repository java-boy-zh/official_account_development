package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.handler.action.TextMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月29日 19:16
 * @Description 用户信息
 * @Version V1.0
 */
@RestController
@Slf4j
public class WxUserController {

    @Resource
    private TextMessageHandler textMessageHandler;


    @RequestMapping("/getSignUpUserInfo")
    public String getSignUpUserInfo(HttpServletRequest request) {
        //获取code
        String code = request.getParameter("code");
        return textMessageHandler.getSignUpUserInfo(code);
    }

}
