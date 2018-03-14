package com.keking.util;

import java.io.*;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件工具类
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class FileUtil {

    /**
     * 获取项目的绝对路径
     *
     * @return
     */
    public static String getAbsolutePath() {
        File file = new File("request-path");
        return StringUtils.substringBeforeLast(file.getAbsolutePath(), "\\");
    }

    /**
     * 获取目标路径下所有的文件名称
     *
     * @param parentPath
     * @return
     */
    public static String[] getFilePathes(String parentPath) {
        File file = new File(parentPath);
        String[] files = file.list();
        return files;
    }

    /**
     * 读取文件的路径
     *
     * @param path
     * @return
     */
    public static String readFilePath(String path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }






}
