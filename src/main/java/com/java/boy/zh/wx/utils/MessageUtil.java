package com.java.boy.zh.wx.utils;

import com.java.boy.zh.wx.entity.WeChatResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月20日 19:00
 * @Description 消息处理
 * @Version V1.0
 */
public class MessageUtil {

    /**
     * 解析微信发来的请求（XML）.
     *
     * @param msg 消息
     * @return map
     */
    public static Map<String, String> parseXml(final String msg) {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        try (InputStream inputStream = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8.name()))) {
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static String convertToXml(WeChatResponse response) {
        try {
            JAXBContext context = JAXBContext.newInstance(WeChatResponse.class);
            Marshaller marshaller = context.createMarshaller();
            // 设置编码格式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 格式化XML输出，设置为false则不换行
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            // 去掉生成的XML文件头部的standalone="yes"
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();
            // 添加XML头部信息
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            marshaller.marshal(response, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("转换XML失败", e);
        }
    }

}
