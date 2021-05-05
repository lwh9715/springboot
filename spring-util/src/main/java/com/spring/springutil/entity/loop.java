package com.spring.springutil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class loop {

    private UUID id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")// 后台传数据到前台
    private Date time;
    private String author;


    public List<loop> loops() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String format = simpleDateFormat.format(new Date());

        loop loop = new loop();
        loop.setId(UUID.randomUUID());
        loop.setName("海贼王");
        loop.setTime(new Date());
        loop.setAuthor("尾田荣一郎");

        loop loop0 = new loop();
        loop0.setId(UUID.randomUUID());
        loop0.setName("海贼王");
        loop0.setTime(new Date());
        loop0.setAuthor("尾田荣一郎");

        loop loop1 = new loop();
        loop1.setId(UUID.randomUUID());
        loop1.setName("七龙珠");
        loop1.setTime(new Date());
        loop1.setAuthor("尾田荣一郎");


        loop loop2 = new loop();
        loop2.setId(UUID.randomUUID());
        loop2.setName("火影忍者");
        loop2.setTime(new Date());
        loop2.setAuthor("尾田荣一郎");

        loop loop3 = new loop();
        loop3.setId(UUID.randomUUID());
        loop3.setName("秦时明月");
        loop3.setTime(new Date());
        loop3.setAuthor("玄机");

        ArrayList<loop> list = new ArrayList<>();
        list.add(loop);
        list.add(loop0);
        list.add(loop1);
        list.add(loop2);
        list.add(loop3);
        return list;
    }

    public static void main(String[] args) {
        loop loop = new loop();
        List<com.plan.bean.loop> list = loop.loops();
        List<loop> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAuthor() != "尾田荣一郎") {
                    list1.add(list.get(i));
            }
        }
        System.out.println(list1.size()+" === "+list1);
        System.out.println(list.size()+" === "+list);
    }
}