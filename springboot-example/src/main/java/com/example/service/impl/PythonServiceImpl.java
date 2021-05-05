package com.example.service.impl;

import com.example.bean.Python;
import com.example.service.PythonService;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;;

/**
 * @Author lwh
 *
 */
@Service
public class PythonServiceImpl implements PythonService {

    public List<Python> pythonUtils(String keyWord){
        Process proc;
        String line = null;
        Python python = new Python();
        List<Python> list = new ArrayList();
        try {
            String[] arg1 = new String[]{"python.exe","G:\\workspace\\springboot-example\\amazon.py" ,keyWord};
            proc = Runtime.getRuntime().exec(arg1);
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                String[] string1 = line.split("==");
//                for (int i = 0; i <string1.length ; i++) {
                    String s = string1[i];
                    String[] string2 = s.split(",");
                    python.setShop(string2[0]);
                    python.setName(string2[1]);
                    python.setEnrollNumber(string2[3]);
                    python.setShop_name2(string2[4]);
                    python.setAddress(string2[5]);
                    list.add(python);
                    System.out.println(list);
                }

//            }
            in.close();
            int i1 = proc.waitFor();
            System.out.println("Process exitValue: " + i1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            //e.printStackTrace();
        }
        return list;
    }
}
