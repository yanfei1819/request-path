package com.keking.document;

import com.keking.file.ReadAllDir;
import com.keking.util.FileUtil;
import com.keking.util.PropertiesUtil;
import com.keking.util.StringUtil;

import java.io.*;
import java.util.List;

/**
 * 文档内容
 * <p>
 * Created by shiyanfei on 2018-03-13.
 */
public class Document {




    //是否直接覆盖文件
    private static boolean RE_WRITE = true;

    /**
     * 写出文档
     */
    public void writerMapper(String projectPath,String outputPath) throws IOException {

//        List<String> listControllerPath = ReadAllDir.readAllFile(TMS_PROJECT);
        List<String> listControllerPath = ReadAllDir.readAllFile(projectPath);

//        File write_file = new File(APP_ALL_PATH);
        File write_file = new File(outputPath);

        if (write_file.exists() && !RE_WRITE) {
            System.out.println("文件已存在  " + write_file.getAbsolutePath());
            return;
        }

        if (RE_WRITE) {
            System.out.println("文件覆盖写入...");
        }

        Writer writer;
        writer = new FileWriter(write_file);
        for (String controllerPath : listControllerPath) {
            String[] filepathes = FileUtil.getFilePathes(controllerPath);

            writer.write("文件路径是:"+controllerPath+"\r\n");

            for (int i = 0; i < filepathes.length; i++) {
                File file = new File(controllerPath + filepathes[i]);
                try {
                    writer.write("----------" + filepathes[i] + "----------\r\n");
                    writer.write(Document.readMapper(file));
                    writer.write("\r\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        writer.close();
        System.out.println("文件写入完成:" + write_file.getAbsolutePath());
    }

    /**
     * 读取文件下的@RequestMapper注解，将整理好的路径内容返回
     *
     * @param f
     */
    public static String readMapper(File f) throws IOException {
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
    public static void outputFileText(String fileStr, String str) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(fileStr));
        Writer os = new OutputStreamWriter(fos, "GBK");
        os.write(str);
        os.flush();
        fos.close();
    }
}
