package com.transform.html;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Map;

/**
@ClassName Html2WordUtil
@Description html 转成 word文档工具，包括数据填充
@Author liujianko
@Date 2019/7/25
**/
class Html2WordUtil {


    /**
     *  html 文件填充数据生成 doc 并返回 html 内容
     * @param path html 文件路径
     * @param tmpName html 文件名称
     * @param desDoc 生成 doc 的全路径
     * @param data 填充数据
     * @return html 内容字符串
     */
    static String html2Word(String path, String tmpName, String desDoc, Map<String, Object> data){
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");
        Template t;
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(desDoc), "UTF-8"));
             StringWriter strOut = new StringWriter()) {
            configuration.setDirectoryForTemplateLoading(new File(path)); // FTL文件所存在的位置
            t = configuration.getTemplate(tmpName); // 文件名
            t.process(data, out);
            t.process(data,strOut);
            return strOut.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    static String html2Word2(String path, String tmpName, String redTmpName, String desDoc, Map<String, Object> data){
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");
        Template t;
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(desDoc), "UTF-8"));
             StringWriter strOut = new StringWriter()) {
            configuration.setDirectoryForTemplateLoading(new File(path)); // FTL文件所存在的位置
            t = configuration.getTemplate(tmpName); // 文件名
            t.process(data, out);
            if (StringUtils.isNotBlank(redTmpName)) {
                t = configuration.getTemplate(redTmpName); // 文件名
            }
            t.process(data, strOut);
            System.out.println(out.toString());
            return strOut.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
