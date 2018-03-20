package com.keking.action;

import com.keking.document.Document;
import com.keking.util.PropertiesUtil;
import com.keking.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 获取请求中的get路径(包括"{}"的路径)
 * <p>
 * Created by shiyanfei on 2018-03-20.
 */
public class GetPathAction {

    // 所读取的所有文件的文件
    private static String APP_ALL_PATH = PropertiesUtil.getPropertis("app_all_path");

    // get路径输出位置
    private static String APP_GET_PATH = PropertiesUtil.getPropertis("app_get_path");

    private static void readGetPath() throws IOException {

        File file = new File(APP_ALL_PATH);
        if(!file.exists()){
            System.out.println("无输出文件，新建输出文件");
            file.createNewFile();
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(APP_ALL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        while (bufferedReader.ready()){
            String mapper = bufferedReader.readLine();

            mapper = StringUtil.trim(mapper);
            if(mapper.contains("{") || mapper.contains("}")){
                stringBuffer.append(mapper);
                stringBuffer.append("\r\n");
            }
        }

        if(StringUtils.isEmpty(stringBuffer.toString())){
            System.out.println("还没有获取到所有路径，请先获取所有路径！");
        }

        Document.outputFileText(APP_GET_PATH,stringBuffer.toString());
    }

    public static void main(String[] args) throws IOException {
        readGetPath();
    }

}
