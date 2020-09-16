package com.rabbitmq;

import com.rabbitmq.entity.Book;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    JestClient jestClient;


    @Test
    public void jest() throws IOException {
        Book book = new Book();
        Index index = new Index.Builder(book).index("index").type("book").build();
        jestClient.execute(index);
    }

    /*
     * 单播模式（点对点）
     * */

    @Test
    public void contextLoads() {
        //message需要自己构造一个，定义消息体内容和消息头
        // rabbitTemplate.send(Exchange,routingKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(Exchange,routingKey,message);
        Map<String, Object> map = new HashMap<>();
        map.put("msg","第一次发送rabbitMq信息");
        map.put("data", Arrays.asList("helloWorld",123,true));
        //对象被默认系列化后发送出去
        rabbitTemplate.convertAndSend("spring.fanout","spring",
                new Book(1,"红楼梦","1980","曹雪芹","2020"));
    }

    //接收数据
    @Test
    public void receive(){
        Object spring = rabbitTemplate.receiveAndConvert("spring#");
        Object string_01 = rabbitTemplate.receiveAndConvert("#spring");
        Object string_02 = rabbitTemplate.receiveAndConvert("spring");
        System.out.println(spring.getClass());
        System.out.println(spring+"==="+string_01+"==="+string_02);
    }


}
