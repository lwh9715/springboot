package com.example.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PythonController {

    public static void main(String[] args) {
        Process proc;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入搜索关键字:");
            String s = scanner.next();
            //设置命令行传入参数
            String[] arg1 = new String[]{"python","C:\\Users\\Administrator\\Desktop\\lwh.py",s};
            proc = Runtime.getRuntime().exec(arg1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
            String line = null;
            //StringBuilder stringBuilder =new StringBuilder();
            while ((line = in.readLine()) !=null){
                System.out.println(line);
                //stringBuilder.append(line);
            }
            //System.out.println(stringBuilder.toString());
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
