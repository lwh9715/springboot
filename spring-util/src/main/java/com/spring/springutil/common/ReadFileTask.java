package com.spring.springutil.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * [功能简述]：遍历获取文件夹内多文件
 *
 * @Author 梁文辉
 * @Date 2021/3/11 18:31
 * @Version 1.0
 */
public class ReadFileTask {

    /**
     * 读取某个文件夹下的所有文件
     */
    public static List<String> readFile(String filepath) throws FileNotFoundException, IOException {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("-----文件夹-----");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        //全路径名称
//                        System.out.println("path=" + readfile.getPath());
//                        System.out.println("absolutepath=" + readfile.getAbsolutePath());
                        //文件名
//                        System.out.println(readfile.getName());

                        list.add(readfile.getPath());
                    } else if (readfile.isDirectory()) {
                        readFile(filepath + "\\" + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        } catch (IOException e) {
            e.getMessage();
        }
        return list;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     */
    static boolean deletefile(String delpath) throws FileNotFoundException, IOException {
        try {

            File file = new File(delpath);
            if (!file.isDirectory()) {
                System.out.println("1-删除文件");
                file.delete();
            } else if (file.isDirectory()) {
                System.out.println("2-删除文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
//                      System.out.println("path=" + delfile.getPath());
//                      System.out.println("absolutepath=" + delfile.getAbsolutePath());
//                      System.out.println("name=" + delfile.getName());
                        delfile.delete();
                        System.out.println("删除文件成功");
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                file.delete();

            }

        } catch (FileNotFoundException e) {
            System.out.println("deletefile()   Exception:" + e.getMessage());
        }
        return true;
    }

}
