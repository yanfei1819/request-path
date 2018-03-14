package com.keking.document;

import com.keking.util.FileUtil;
import com.keking.util.PropertiesUtil;
import com.keking.util.StringUtil;

import java.io.*;

/**
 * 文档内容
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class Document {

    //项目路径
//    private static String PROJECT_PATH = "F:/workplace/tms/ydjf-tms-hefei.git/src";
    private static String PROJECT_PATH = "F:/workplace/yudian-app/app-core/src";
    //扫描包
//    private static String SACNNER_PACKAGE = "/main/java/com/yudianbank/tms/controller/";
    private static String SACNNER_PACKAGE = "/main/java/com/yudianbank/app/web/controller/";

    //输出文件的映射路径的位置
    private static String WRITE_TXT_PATH = PropertiesUtil.getPropertis("tms_url_out_path");

    private static String APP_GETURL = PropertiesUtil.getPropertis("app_geturl_out_path");
    //是否直接覆盖文件
    private static boolean RE_WRITE = true;

    /**
     * 写出文档
     */
    private void writerMapper() {
        File write_file = new File(WRITE_TXT_PATH);
        if (write_file.exists() && !RE_WRITE) {
            System.out.println("文件已存在  " + write_file.getAbsolutePath());
            return;
        }

        if (RE_WRITE) {
            System.out.println("文件覆盖写入...");
        }

        String[] filepathes = FileUtil.getFilePathes(PROJECT_PATH + SACNNER_PACKAGE);
        Writer writer = null;
        try {
            writer = new FileWriter(write_file);
            for (int i = 0; i < filepathes.length; i++) {
                File file = new File(PROJECT_PATH + SACNNER_PACKAGE + filepathes[i]);
                try {
                    writer.write("\r\n");
                    writer.write("=======" + filepathes[i] + "========\r\n");
                    writer.write(readMapper(file));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("文件写入完成:" + write_file.getAbsolutePath());
    }

    /**
     * 读取文件下的@RequestMapper注解，将整理好的路径内容返回
     *
     * @param f
     */
    private String readMapper(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuffer sb = new StringBuffer();
        int i = 0;
        String classpath = "";

        while (br.ready()) {
            String mapper = br.readLine();
            mapper = StringUtil.trim(mapper);
            if (mapper.startsWith("@RequestMapping")) {
                String m = mapper.substring("@RequestMapping(".length(), mapper.length() - 1);
                m = m.replace("value=", "");
                m = m.replace("method=RequestMethod.", "");
                m = m.replace(",method={RequestMethod.GET}", "");
                m = m.replace("\"", "");   // 将路径中的"去除

                if (i == 0) {
                    classpath = m;
                    i++;
                    continue;
                } else {
                    sb.append(i + ". ");  // 序号
                }
                sb.append(classpath);
                sb.append(m);
                if (i > 0) {
                    if (!(m.endsWith("GET") || m.endsWith("POST"))) {
                        sb.append(",GET");
                    }
                }
                sb.append("\r\n");
                i++;
            }

        }
        br.close();
        return sb.toString();
    }

    /**
     * 项目文件中获取url
     */
    public static void getUrls() {
        System.out.println("===========URL提取开始=========");
        Document document = new Document();
        document.writerMapper();
        System.out.println("===========URL提取结束=========");
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
     * 将文本内容写入文件中
     *
     * @param fileStr
     * @param str
     * @throws IOException
     */
    private static void outputFileText(String fileStr, String str) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(fileStr));
        Writer os = new OutputStreamWriter(fos, "GBK");
        os.write(str);
        os.flush();
        fos.close();
    }

}
