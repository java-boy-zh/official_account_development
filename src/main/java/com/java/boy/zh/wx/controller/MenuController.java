package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.entity.menu.Button;
import com.java.boy.zh.wx.entity.menu.MenuButton;
import com.java.boy.zh.wx.entity.menu.WxMenuResponse;
import com.java.boy.zh.wx.service.WxMenuService;
import com.java.boy.zh.wx.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月29日 10:28
 * @Description 自定义菜单控制controller
 * @Version V1.0
 * 微信公众号自定义菜单控制器
 * 提供菜单的创建、查询、删除等接口
 */
@RestController
@RequestMapping("/wx/menu")
public class MenuController {

    @Resource
    private WxMenuService wxMenuService;

    /**
     * 创建自定义菜单
     *
     * @param menuButton  菜单配置信息
     * @return 创建结果
     */
    @PostMapping("/create")
    public WxMenuResponse createMenu(@RequestBody MenuButton menuButton) {
        String accessToken = TokenUtil.getAccessToken();
        return wxMenuService.createMenu(menuButton, accessToken);
    }

    /**
     * 查询自定义菜单
     *
     * @return 当前的菜单配置
     */
    @GetMapping("/query")
    public MenuButton queryMenu() {
        String accessToken = TokenUtil.getAccessToken();
        return wxMenuService.queryMenu(accessToken);
    }

    /**
     * 删除自定义菜单
     *
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public WxMenuResponse deleteMenu() {
        String accessToken = TokenUtil.getAccessToken();
        return wxMenuService.deleteMenu(accessToken);
    }

    /**
     * 创建默认菜单
     *
     * @return 创建结果
     */
    @PostMapping("/create/default")
    public WxMenuResponse createDefaultMenu() {
        String accessToken = TokenUtil.getAccessToken();

        // 创建默认菜单配置
        MenuButton menuButton = new MenuButton();
        List<Button> buttons = new ArrayList<>();

        // 创建第一个一级菜单（点击类型）
        Button button1 = new Button();
        button1.setName("菜单一");
        button1.setType("click");
        button1.setKey("menu1");

        // 创建第二个一级菜单（链接类型）
        Button button2 = new Button();
        button2.setName("菜单二");
        button2.setType("view");
        button2.setUrl("http://www.baidu.com");

        // 创建第三个一级菜单（带子菜单）
        Button button3 = new Button();
        button3.setName("更多");
        java.util.List<Button> subButtons = new java.util.ArrayList<>();

        // 创建子菜单1
        Button subButton1 = new Button();
        subButton1.setType("click");
        subButton1.setName("子菜单1");
        subButton1.setKey("sub_menu1");

        // 创建子菜单2
        Button subButton2 = new Button();
        subButton2.setType("view");
        subButton2.setName("子菜单2");
        subButton2.setUrl("https://www.baidu.com");

        // 创建子菜单3
        Button subButton3 = new Button();
        subButton3.setType("pic_photo_or_album");
        subButton3.setName("发图");
        subButton3.setKey("subButton3");

        subButtons.add(subButton1);
        subButtons.add(subButton2);
        subButtons.add(subButton3);
        button3.setSub_button(subButtons);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        menuButton.setButton(buttons);

        return wxMenuService.createMenu(menuButton, accessToken);
    }
}
