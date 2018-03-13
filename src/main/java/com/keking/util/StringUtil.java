package com.keking.util;

import java.io.File;

/**
 * 字符串工具类
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class StringUtil {

    /**
     * 去掉空白字符
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        str = str.replace(" ", "")
                .replace("\t", "")
                .trim();
        return str;
    }
}
