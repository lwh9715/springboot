package com.spring.springutil;

import com.alibaba.fastjson.JSON;
import com.spring.springutil.entity.Season;
import com.spring.springutil.util.HttpUtils;
import com.spring.springutil.util.ImageUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringUtilApplicationTests {

    @Test
    void binary_pic() {
        //转bash64编码
        String imageStr = ImageUtils.GetImageStr("C:\\Users\\Administrator\\Desktop\\微信截图_20210316221556.jpg");
//		System.out.println(imageStr);

        //生成图片
        boolean generateImage = ImageUtils.GenerateImage(imageStr, "C:\\Users\\Administrator\\Desktop\\images\\girl.jpg");
        System.out.println(generateImage);

        //图片转二进制
        byte[] bytes = ImageUtils.image2byte("C:\\Users\\Administrator\\Desktop\\images\\girl.jpg");
        System.out.println(bytes);

        byte[] imageBinary = ImageUtils.getImageBinary("C:\\Users\\Administrator\\Desktop\\微信截图_20210316224620.png");
        System.out.println(imageBinary);

        String images_file = byteToString(imageBinary);

        Map<String, String> map = new HashMap<>();
        map.put("images", images_file);

        HttpUtils.doPost("https://imgs-sandbox.intsig.net/icr/v3/recognize_id_card", map);

    }


    private static String byteToString(byte[] bytes) {
        if (null == bytes || bytes.length == 0) {
            return "";
        }
        String strContent = "";
        try {
            strContent = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strContent;
    }

    @Test
    void binary() {
        File source = new File("C:\\Users\\Administrator\\Desktop\\微信截图_20210316221556.jpg");
        File desk = new File("C:\\Users\\Administrator\\Desktop\\images");
        if (!desk.exists()) {
            desk.mkdir();
        }

        try {
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(new File("c:/Users/Administrator/Desktop/images/Bliss.jpg"));

            int ch = inputStream.read();
            while (ch != -1) {
                outputStream.write(ch);
                ch = inputStream.read();
            }

            inputStream.close();
            outputStream.close();
            System.out.println("图片复制成功！");
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在：" + e.getMessage());
        } catch (IOException e) {
            System.out.println("文件读取错误：" + e.getMessage());
        }

    }


    //string方法的使用，截取，字符串转换字符
    @Test
    public void contextLoads() {
        String str = "http://www.innocom.gov.cn/gqrdw/c101430/202012/16867fe91eb543e5b606428c57a8c905";
        String str_01 = "16867fe91eb543e5b606428c57a8c905/files/1af40dbd2cce49309920916b62e19e70.pdf";
        String s = str.substring(47);//截取相同字符串后面的数据16867fe91eb543e5b606428c57a8c905
        char[] chars = s.toCharArray();//转换成字符
        String s1 = str_01.substring(chars.length);//获取字符长度,根据字符长度截取后面的数据
        System.out.println(str);
        System.out.println(str_01);
        System.out.println(s);
        System.out.println(s1);
    }

    //string方法的使用，计算大>小字符串重复出现的次数
    @Test
    void contextLoads1() {
        String max = "http://www.innocom.gov.cn/gqrdw/c101430/202012/innocom/16867fe91eb543e5b606428c57a8c905";
        String min = "innocom";
        int count = 0;//定义计数器
        int index = 0;//定义索引
        while ((index = max.indexOf(min)) != -1) {
            count++;
            max = max.substring(index + min.length());//index是int类型,代表从max字符串的11位+min的长度开始截取
        }
        System.out.println(count);
    }

    @Test
    public void jsonTest() {

        String mix = "car";

        Season season = new Season();
        season.setId(26);
        season.setUsername("relax");
        season.setPassword("root");
        season.setSpring("春天");
        season.setSummer("夏天");
        season.setAutumn("秋天");
        season.setWinter("冬天");
        //System.out.println(season);

        Season season_01 = new Season();
        season_01.setId(27);
        season_01.setUsername("cat");
        season_01.setPassword("123");
        season_01.setSpring("春天");
        season_01.setSummer("夏天");
        season_01.setAutumn("秋天");
        season_01.setWinter("冬天");

        //System.out.println(season_01);
        //System.out.println("打印："+season);
        String s = JSON.toJSONString(season);
        //System.out.println("json格式："+s);
        //json格式：{"autumn":"秋天","id":26,"password":"root","spring":"春天","summer":"夏天","username":"relax","winter":"冬天"}
        Season object = JSON.parseObject(s, Season.class);
        //System.out.println("反序列化："+object);
        //反序列化：Season{Id=26, Username='relax', Password='root', Spring='春天', Summer='夏天', Autumn='秋天', Winter='冬天'}


        /*HashMap<String, Object> map_01 = new HashMap<>();
        map_01.put("001",11);
        map_01.put("001_结果","car");

        HashMap<String, Object> map_02 = new HashMap<>();
        map_02.put("002",27);
        map_02.put("002_结果","cat");

        HashMap<String, Object> map_03 = new HashMap<>();
        map_03.put("003_结果","car");
        map_03.put("003",28);

        *//*System.out.println(map_01);
        System.out.println(map_02);
        System.out.println(map_03);*//*

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        list.add(map_01);
        list.add(map_02);
        list.add(map_03);
        System.out.println("json格式前"+list);
        String map = JSON.toJSONString(list);
        System.out.println("json格式后"+map);
        String[] split = map.split(",");
        String s1 = split[0].replace("[{", "");
        String s2 = split[1].replace("}", "").replaceAll("\"","");
        String s3 = split[2].replace("{", "");
        String s4 = split[3].replace("}", "").replaceAll("\"","");;
        String[] split_01 = s1.split(":");
        String[] split_02 = s2.split(":");
        String[] split_03 = s3.split(":");
        String[] split_04 = s4.split(":");
        char[] chars_01 = split_01[1].toCharArray();
        char[] chars_02 = split_02[1].toCharArray();
        char[] chars_03 = split_03[1].toCharArray();
        char[] chars_04 = split_04[1].toCharArray();
        *//*System.out.println(chars_01[1]);
        System.out.println(chars_02[1]);
        System.out.println(chars_03[1]);
        System.out.println(chars_04[1]);*/

        /*System.out.println(new Date());
        String date = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);//"2014-05-29 21:36:24"
        String date1 = JSON.toJSONStringWithDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss.S");//"2020-12-26 15:33:02.655"
        System.out.println(date);
        System.out.println(date1);*/

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        ParsePosition position = new ParsePosition(0);
        Date parse = simpleDateFormat.parse(format, position);
        System.out.println(parse);
    }

}
