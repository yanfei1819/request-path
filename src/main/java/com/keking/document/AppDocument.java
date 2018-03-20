//package com.keking.document;
//
//import com.keking.file.ReadAllDir;
//import com.keking.util.FileUtil;
//import com.keking.util.PropertiesUtil;
//
//import java.io.*;
//import java.util.List;
//
///**
// * 文档内容
// * <p>
// * Created by shiyanfei on 2018-03-13.
// */
//public class AppDocument {
//
//    //输出文件的映射路径的位置
//    private static String APP_ALL_PATH = PropertiesUtil.getPropertis("app_all_path");
//
//    //是否直接覆盖文件
//    private static boolean RE_WRITE = true;
//
//    private static String APP_PROJECT = PropertiesUtil.getPropertis("app_project");
//
//    private static String TMS_PROJECT = PropertiesUtil.getPropertis("tms_project");
//
//    /**
//     * 写出文档
//     */
//    public void writerMapper() throws IOException {
//
//        List<String> listControllerPath = ReadAllDir.readAllFile(TMS_PROJECT);
//
//        File write_file = new File(APP_ALL_PATH);
//
//        if (write_file.exists() && !RE_WRITE) {
//            System.out.println("文件已存在  " + write_file.getAbsolutePath());
//            return;
//        }
//
//        if (RE_WRITE) {
//            System.out.println("文件覆盖写入...");
//        }
//
//        Writer writer;
//        writer = new FileWriter(write_file);
//        for (String controllerPath : listControllerPath) {
//            String[] filepathes = FileUtil.getFilePathes(controllerPath);
//
//            writer.write("文件路径是:"+controllerPath+"\r\n");
//
//            for (int i = 0; i < filepathes.length; i++) {
//                File file = new File(controllerPath + filepathes[i]);
//                try {
//                    writer.write("----------" + filepathes[i] + "----------\r\n");
//                    writer.write(TmsDocument.readMapper(file));
//                    writer.write("\r\n");
//                    writer.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        writer.close();
//        System.out.println("文件写入完成:" + write_file.getAbsolutePath());
//    }
//
//
//}
