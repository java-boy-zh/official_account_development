package com.java.boy.zh.wx.service;

import com.java.boy.zh.wx.entity.templat.IndustryInfo;
import com.java.boy.zh.wx.entity.templat.TemplateInfo;
import com.java.boy.zh.wx.entity.templat.WxTemplateMessage;
import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 微信模板消息服务接口
 * @Version V1.0
 */
public interface WxTemplateMessageService {

    /**
     * 设置所属行业
     */
    boolean setIndustry(String industryId1, String industryId2);

    /**
     * 获取设置的行业信息
     */
    IndustryInfo getIndustryInfo();

    /**
     * 获得模板ID
     */
    String getTemplateId(String templateIdShort, List<String> keywordList);

    /**
     * 获取模板列表
     */
    List<TemplateInfo> getTemplateList();
    
    /**
     * 删除模板
     */
    boolean deleteTemplate(String templateId);
    
    /**
     * 发送模板消息
     */
    boolean sendTemplateMessage(WxTemplateMessage message);
} 