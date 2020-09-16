package com.rabbitmq.service;

import com.rabbitmq.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    //监听消息队列的内容（#spring）
    @RabbitListener(queues = "#spring")
    public void receive(Book book){
        System.out.println("收到消息！！！"+book);
    }

    @RabbitListener(queues = "#spring")
    public void receive_01(Message message){
        //监听头部信息
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
