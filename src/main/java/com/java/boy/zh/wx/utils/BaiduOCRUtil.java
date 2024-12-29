package com.java.boy.zh.wx.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月29日 12:15
 * @Description 百度图片识别
 * @Version V1.0
 */
@Slf4j
public class BaiduOCRUtil {

    public static final String APP_ID = "";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    public static String getOcr(String imageUrl) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        JSONObject result = client.vehicleAttrUrl(imageUrl, new HashMap<String, String>());

        log.info("getAccessToken : result-> {}", result);


        JSONArray words_result = result.getJSONArray("words_result");
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Object> iterator = words_result.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            stringBuilder.append(next.getJSONArray("words") + " ");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        getOcr("https://ts1.cn.mm.bing.net/th/id/R-C.f02d35dfdb3ec95b2b66fb6522961416?rik=zMmwTLLGc6rXuQ&riu=http%3a%2f%2fimage.biaobaiju.com%2fuploads%2f20180531%2f01%2f1527700537-rhdZamGQRx.jpg&ehk=wNWyL%2b%2br6JQb2x%2b94fsU3IF6ZCtlUqZHPHwIShNjPnI%3d&risl=&pid=ImgRaw&r=0");
    }

}
