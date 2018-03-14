package com.keking.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json数据转换工具类
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class JsonUtil {

    /**
     * string -> JSONObject
     *
     * @param str
     * @return
     */
    public static JSONObject stringToJSONObject(String str) {
        return JSONObject.parseObject(str);
    }

    /**
     * string -> JSONArray
     *
     * @param str
     * @return
     */
    public static JSONArray stringToJSONArray(String str) {
        return JSON.parseArray(str);
    }

    /**
     * object -> JSONArray
     *
     * @param o
     * @return
     */
    public static JSONArray objectToJsonArray(Object o){
        return JSON.parseArray((String) o);
    }

    /**
     * 通过路径获取内容
     *
     * @param path
     * @return
     */
    public static JSONArray getContent(String path){
        String jsonContent = FileUtil.readFilePath(path);
        return stringToJSONArray(jsonContent);
    }


}
