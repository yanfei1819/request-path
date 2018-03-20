package com.keking.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取目录下面的controller文件
 * <p>
 * Created by shiyanfei on 2018-03-20.
 */
public class ReadAllDir {

    public static List<String> readAllFile(String filePath) {

        List<String> controllerPath = new ArrayList<String>();
        try {
            File file = new File(filePath);

            if (file.isDirectory()) {
                String[] files = file.list();

                for (int i = 0; i < files.length; i++) {

                    File readFile = new File(filePath + "\\" + files[i]);

                    if(readFile.isDirectory()){
                        String[] readFiles = readFile.list();
                        for (int j=0;j<readFiles.length;j++){
                            File readFile2 = new File(readFiles[j]);
                            if(readFile2.getName().equals("web")){
                                String path1 = filePath+"\\"+files[i]+"\\"+readFile2.getName()+"\\"+"controller\\";
                                controllerPath.add(path1);
                            }else if(readFile2.getName().equals("controller")){
                                String path2 = filePath+"\\"+files[i]+"\\"+readFile2.getName()+"\\";
                                controllerPath.add(path2);
                            }
                        }
                    }

                }
            } else {
                System.out.println(file.getPath());
            }

        }catch (Exception e){
            e.getMessage();
        }
        return controllerPath;
    }

    public static void main(String[] args) {
//        List<String> list = readAllFile("F:\\workplace\\yudian-app\\app-core\\src\\main\\java\\com\\yudianbank");
        List<String> list = readAllFile("F:\\workplace\\tms\\ydjf-tms-hefei.git\\src\\main\\java\\com\\yudianbank");
        for (String path:list) {
            System.out.println(path);
        }
    }


}
