package com.java.boy.zh.wx.utils;

import com.java.boy.zh.wx.entity.WxToken;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 19:23
 * @Description Token获取工具类
 * @Version V1.0
 */
@Slf4j
public class TokenUtil {

    private static WxToken wxToken = new WxToken();

    private static final String APP_ID = "wx52fa481c17d84968";
    private static final String APP_SECRET = "4dcfd4744309414cdd5115f7fd09debf";

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static WxToken getWxToken() {
        if (wxToken != null && !wxToken.isExpired()) {
            return wxToken;
        }
        String url = String.format(URL, APP_ID, APP_SECRET);
        String result = HttpUtil.doGet(url);
        log.info("result -> {}", result);

        JSONObject jsonObject = JSONObject.fromObject(result);
        String access_token = jsonObject.getString("access_token");
        Long expires_in = jsonObject.getLong("expires_in");

        wxToken.setAccessToken(access_token);
        wxToken.setExpiresIn(expires_in);

        return wxToken;
    }

    public static String getAccessToken() {
        if (wxToken == null || wxToken.isExpired()) {
            wxToken = getWxToken();
        }
        return wxToken.getAccessToken();
    }

    public static void main(String[] args) {
        String accessToken = getAccessToken();
        System.out.println("accessToken1 = " + accessToken);
        accessToken = getAccessToken();
        System.out.println("accessToken2 = " + accessToken);
    }

}
