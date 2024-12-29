package com.java.boy.zh.wx.controller;

import com.java.boy.zh.wx.entity.result.Result;
import com.java.boy.zh.wx.entity.result.ResultEnum;
import com.java.boy.zh.wx.entity.templat.IndustryInfo;
import com.java.boy.zh.wx.entity.templat.TemplateInfo;
import com.java.boy.zh.wx.entity.templat.WxTemplateMessage;
import com.java.boy.zh.wx.service.WxTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 微信模板消息控制器
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/wx/template")
public class WxTemplateMessageController {

    @Resource
    private WxTemplateMessageService templateMessageService;

    /**
     * 设置所属行业
     */
    @PostMapping("/industry")
    public Result<Void> setIndustry(@RequestParam String industryId1,
                                    @RequestParam String industryId2) {
        boolean success = templateMessageService.setIndustry(industryId1, industryId2);
        return success ? Result.ok() : Result.fail(ResultEnum.INDUSTRY_SET_FAIL);
    }

    /**
     * 获取行业信息
     */
    @GetMapping("/industry")
    public Result<IndustryInfo> getIndustryInfo() {
        IndustryInfo info = templateMessageService.getIndustryInfo();
        return info != null ? Result.ok(info) : Result.fail(ResultEnum.WX_ERROR);
    }

    /**
     * 获取模板ID
     */
    @PostMapping("/template-id")
    public Result<String> getTemplateId(@RequestParam String templateIdShort,
                                        @RequestParam List<String> keywordList) {
        String templateId = templateMessageService.getTemplateId(templateIdShort, keywordList);
        return templateId != null ? Result.ok(templateId) : Result.fail(ResultEnum.TEMPLATE_NOT_FOUND);
    }

    /**
     * 获取模板列表
     */
    @GetMapping("/list")
    public Result<List<TemplateInfo>> getTemplateList() {
        List<TemplateInfo> templates = templateMessageService.getTemplateList();
        return Result.ok(templates);
    }

    /**
     * 删除模板
     */
    @DeleteMapping("/{templateId}")
    public Result<Void> deleteTemplate(@PathVariable String templateId) {
        boolean success = templateMessageService.deleteTemplate(templateId);
        return success ? Result.ok() : Result.fail(ResultEnum.TEMPLATE_NOT_FOUND);
    }

    /**
     * 发送模板消息
     */
    @PostMapping("/send")
    public Result<Void> sendTemplateMessage(@RequestBody WxTemplateMessage message) {
        boolean success = templateMessageService.sendTemplateMessage(message);
        return success ? Result.ok() : Result.fail(ResultEnum.TEMPLATE_SEND_FAIL);
    }
} 