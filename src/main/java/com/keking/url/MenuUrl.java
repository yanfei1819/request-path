package com.keking.url;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keking.util.FileUtil;
import com.keking.util.JsonUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 获取meunjson中的url
 *
 * Created by shiyanfei on 2018-03-13.
 */
public class MenuUrl {
    private static final String TMS_PATH = FileUtil.getAbsolutePath() + "\\src\\dataURL\\tms.json";

    public static Set<String> getUrls(JSONArray jsonArray) {
        Set<String> urls = new HashSet<String>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String uri = jsonObject.get("url").toString();
                if(uri != null && uri != "null"){
                    urls.add(uri);
                }
                Object menus = jsonObject.get("menus");
                if (menus != null && menus.toString() != "null") {
                    urls.addAll(getUrls(JsonUtil.objectToJsonArray(menus)));
                }
            }
        }
        return urls;
    }
}