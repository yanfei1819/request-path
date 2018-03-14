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
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
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

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String readFileText(String fileName) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
        ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(buffer)) != -1) {
            memStream.write(buffer, 0, len);
        }
        byte[] data = memStream.toByteArray();
        bufferedInputStream.close();
        memStream.close();
        bufferedInputStream.close();
        System.out.println();
        return new String(data);
    }

    /**
     * 写出文件内容
     *
     * @param fileStr
     * @param str
     * @throws IOException
     */
    public static void outputFileText(String fileStr, String str) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileStr));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Writer os = null;
        try {
            os = new OutputStreamWriter(fos, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        os.write(str);
        os.flush();
        fos.close();

    }


}
