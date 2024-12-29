package com.java.boy.zh.wx.service.impl;

import com.java.boy.zh.wx.entity.menu.MenuButton;
import com.java.boy.zh.wx.entity.menu.WxMenuResponse;
import com.java.boy.zh.wx.service.WxMenuService;
import com.java.boy.zh.wx.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * 微信公众号自定义菜单服务类
 * 提供创建、查询、删除自定义菜单的功能
 */
@Service
@Slf4j
public class WxMenuServiceImpl implements WxMenuService {

    /**
     * 创建菜单的接口URL
     */
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 查询菜单的接口URL
     */
    private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**
     * 删除菜单的接口URL
     */
    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 创建自定义菜单
     *
     * @param menuButton  菜单配置，包含一级菜单和二级菜单的配置信息
     * @param accessToken 调用接口凭证
     * @return 微信服务器的响应结果
     */
    public WxMenuResponse createMenu(MenuButton menuButton, String accessToken) {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
        String button = JSONObject.fromObject(menuButton).toString();

        String result = HttpUtil.doPost(url, button, MediaType.APPLICATION_JSON);
        log.info("result -> {}", result);
        return null;
    }

    /**
     * 查询自定义菜单配置
     *
     * @param accessToken 调用接口凭证
     * @return 当前的菜单配置信息
     */
    public MenuButton queryMenu(String accessToken) {
        String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", accessToken);
//        return restTemplate.getForObject(url, MenuButton.class);
        return null;
    }

    /**
     * 删除自定义菜单
     *
     * @param accessToken 调用接口凭证
     * @return 微信服务器的响应结果
     */
    public WxMenuResponse deleteMenu(String accessToken) {
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
//        return restTemplate.getForObject(url, WxMenuResponse.class);
        return null;
    }
} 