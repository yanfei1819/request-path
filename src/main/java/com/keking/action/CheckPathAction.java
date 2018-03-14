package com.keking.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keking.url.MenuUrl;
import com.keking.util.FileUtil;
import com.keking.util.JsonUtil;

import java.util.Set;

/**
 * 获取SpringMVC的接口地址路径的执行类
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class CheckPathAction {

    // 标准路径
    private static final String STANDARD_PATH = FileUtil.getAbsolutePath() + "\\src\\dataURL\\standard.json";

    // 被读取的路径
    private static final String TMS_PATH = FileUtil.getAbsolutePath() + "\\src\\dataURL\\controllerMapping.json";

    /**
     * 对比接口路径
     */
    private void checkPath() {

        JSONArray standard = JsonUtil.getContent(STANDARD_PATH);
        // 从文件扫描生成的json格式的url，然后再取其中的url
        JSONArray jsonArray = JsonUtil.getContent(TMS_PATH);

        int successCount = 0;
        int failCount = 0;

        for (int i = 0; i < standard.size(); i++) {

            // 标准的url
            JSONObject standardJson = standard.getJSONObject(i);

            // 这是从meun文件下扫描得到的url
            Set<String> tmsuri = MenuUrl.getUrls(jsonArray);

            if(!tmsuri.contains(standardJson.get("url"))){
                System.err.println("======匹配失败的是：" + standardJson.get("url") + "======");
                failCount++;
            }else{
                System.out.println("======匹配成功的是：" + "[" + i + "]url=" + standardJson.get("url") + "======");
                successCount++;
            }
        }
        System.out.println("======匹配成功的数量是：" + successCount + "======");
        System.out.println("======匹配失败的数量是：" + failCount + "======");
    }
}
