package com.java.boy.zh.wx.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.boy.zh.wx.entity.templat.IndustryInfo;
import com.java.boy.zh.wx.entity.templat.TemplateInfo;
import com.java.boy.zh.wx.entity.templat.WxTemplateMessage;
import com.java.boy.zh.wx.service.WxTemplateMessageService;
import com.java.boy.zh.wx.utils.HttpUtil;
import com.java.boy.zh.wx.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 微信模板消息服务实现类
 * @Version V1.0
 */
@Slf4j
@Service
public class WxTemplateMessageServiceImpl implements WxTemplateMessageService {

    private final String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";
    private final String GET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";
    private final String GET_TEMPLATE_ID_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";
    private final String GET_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
    private final String DEL_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s";
    private final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    @Override
    public boolean setIndustry(String industryId1, String industryId2) {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(SET_INDUSTRY_URL, accessToken);

            Map<String, String> params = new HashMap<>();
            params.put("industry_id1", industryId1);
            params.put("industry_id2", industryId2);

            String response = HttpUtil.doPost(url, JSON.toJSONString(params), MediaType.APPLICATION_JSON);
            JSONObject jsonObject = JSON.parseObject(response);

            return jsonObject.getInteger("errcode") == 0;
        } catch (Exception e) {
            log.error("设置行业信息异常", e);
            return false;
        }
    }

    @Override
    public IndustryInfo getIndustryInfo() {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(GET_INDUSTRY_URL, accessToken);

            String response = HttpUtil.doGet(url);
            JSONObject jsonObject = JSON.parseObject(response);

            if (jsonObject.getInteger("errcode") != null
                    &&
                    jsonObject.getInteger("errcode") == 0) {
                return null;
            }
            log.info("获取行业信息成功: {}", response);
            return JSON.parseObject(response, IndustryInfo.class);
        } catch (Exception e) {
            log.error("获取行业信息异常", e);
            return null;
        }
    }

    @Override
    public String getTemplateId(String templateIdShort, List<String> keywordList) {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(GET_TEMPLATE_ID_URL, accessToken);

            Map<String, Object> params = new HashMap<>();
            params.put("template_id_short", templateIdShort);
            params.put("keyword_name_list", keywordList);

            String response = HttpUtil.doPost(url, JSON.toJSONString(params), MediaType.APPLICATION_JSON);
            JSONObject jsonObject = JSON.parseObject(response);

            if (jsonObject.getInteger("errcode") != null
                    &&
                    jsonObject.getInteger("errcode") == 0) {
                return null;
            }
            log.info("获取模板ID成功: {}", response);
            return jsonObject.getString("template_id");
        } catch (Exception e) {
            log.error("获取模板ID异常", e);
            return null;
        }
    }

    @Override
    public List<TemplateInfo> getTemplateList() {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(GET_TEMPLATE_URL, accessToken);

            String response = HttpUtil.doGet(url);
            JSONObject jsonObject = JSON.parseObject(response);

            if (jsonObject.getInteger("errcode") != null
                    &&
                    jsonObject.getInteger("errcode") == 0) {
                return Collections.emptyList();
            }

            log.info("获取模板列表成功: {}", response);
            return JSON.parseArray(jsonObject.getString("template_list"), TemplateInfo.class);
        } catch (Exception e) {
            log.error("获取模板列表异常", e);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deleteTemplate(String templateId) {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(DEL_TEMPLATE_URL, accessToken);

            Map<String, String> params = new HashMap<>();
            params.put("template_id", templateId);

            String response = HttpUtil.doPost(url, JSON.toJSONString(params), MediaType.APPLICATION_JSON);
            JSONObject jsonObject = JSON.parseObject(response);

            return jsonObject.getInteger("errcode") == 0;
        } catch (Exception e) {
            log.error("删除模板异常", e);
            return false;
        }
    }

    @Override
    public boolean sendTemplateMessage(WxTemplateMessage message) {
        try {
            String accessToken = TokenUtil.getAccessToken();
            String url = String.format(SEND_TEMPLATE_URL, accessToken);

            String response = HttpUtil.doPost(url, JSON.toJSONString(message), MediaType.APPLICATION_JSON);
            JSONObject jsonObject = JSON.parseObject(response);

            if (jsonObject.getInteger("errcode") != null
                    &&
                    jsonObject.getInteger("errcode") == 0) {
                return false;
            }

            log.info("发送模板消息成功: {}", response);
            return true;
        } catch (Exception e) {
            log.error("发送模板消息异常", e);
            return false;
        }
    }
} 