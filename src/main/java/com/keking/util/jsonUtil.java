package com.keking.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json数据转换工具类
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class jsonUtil {

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


}
