package com.keking.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shiyanfei on 2018-03-14.
 */
public class DateUtil {

    private static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式化
     *
     * @return
     */
    public static String dateFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyyMMddHHmmss);
        return simpleDateFormat.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(dateFormat());
    }
}
