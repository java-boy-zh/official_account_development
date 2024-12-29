package com.java.boy.zh.wx.service;

import com.java.boy.zh.wx.entity.menu.MenuButton;
import com.java.boy.zh.wx.entity.menu.WxMenuResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 微信公众号自定义菜单服务类
 * 提供创建、查询、删除自定义菜单的功能
 */
public interface WxMenuService {

    /**
     * 创建自定义菜单
     *
     * @param menuButton  菜单配置，包含一级菜单和二级菜单的配置信息
     * @param accessToken 调用接口凭证
     * @return 微信服务器的响应结果
     */
    WxMenuResponse createMenu(MenuButton menuButton, String accessToken);

    /**
     * 查询自定义菜单配置
     *
     * @param accessToken 调用接口凭证
     * @return 当前的菜单配置信息
     */
    MenuButton queryMenu(String accessToken);

    /**
     * 删除自定义菜单
     *
     * @param accessToken 调用接口凭证
     * @return 微信服务器的响应结果
     */
    WxMenuResponse deleteMenu(String accessToken);
} 