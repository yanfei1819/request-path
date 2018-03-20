package com.keking.action;

import com.keking.document.Document;
import com.keking.util.PropertiesUtil;

import java.io.IOException;

/**
 * 获取请求地址执行层
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class AllPathAction {

    // 项目路径
    private static String APP_PROJECT = PropertiesUtil.getPropertis("app_project");
    private static String TMS_PROJECT = PropertiesUtil.getPropertis("tms_project");

    // 输出文件的映射路径的位置
    private static String APP_ALL_PATH = PropertiesUtil.getPropertis("app_all_path");
    private static String TMS_ALL_PATH = PropertiesUtil.getPropertis("tms_all_path");


    public static void main(String[] args) throws IOException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>URL提取开始>>>>>>>>>>>>>>>>>>>>>");
        Document document = new Document();
        document.writerMapper(APP_PROJECT, APP_ALL_PATH);
        document.writerMapper(TMS_PROJECT, TMS_ALL_PATH);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<URL提取结束<<<<<<<<<<<<<<<<<<<<<");
    }
}
