package com.java.boy.zh.wx.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 笑话接口示例代码
 */
public class JokeUtil {

    //随机笑话
    public static final String URL = "http://v.juhe.cn/joke/randJoke.php?key=%s";

    //申请接口的请求key
    // TODO: 您需要改为自己的请求key
    public static final String KEY = "e2f581380154c10257f6e74b5080c668";

    /**
     * 随机笑话
     */
    public static String getJoke() {

        StringBuilder sb = new StringBuilder();

        //发送http请求的url
        String url = String.format(URL, KEY);
        final String response = HttpUtil.doGet(url);
        try {
            JSONObject jsonObject = JSONObject.fromObject(response);
            int error_code = jsonObject.getInt("error_code");
            if (error_code == 0) {
                JSONArray result = jsonObject.getJSONArray("result");
                for (Object o : result) {
                    JSONObject jsonObject1 = JSONObject.fromObject(o);
                    Object content = jsonObject1.get("content");
                    sb.append(content);
                    if (sb.length() > 0) return sb.toString();
                }

                return sb.toString();
            } else {
                System.out.println("调用接口失败：" + jsonObject.getString("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

