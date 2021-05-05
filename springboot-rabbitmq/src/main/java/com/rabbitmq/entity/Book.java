package com.rabbitmq.entity;


import lombok.Data;

//使用SpringData的时候，它需要在实体类中设置indexName 和type ，如果和传统型数据库比较的话，就相当于库和表。
// 需要注意的是indexName和type都必须是小写!!!
//@Document(indexName = "bookindex" ,type = "book")
@Data
public class Book {

    private Integer id;
    private String name;
    private String age;
    private String description;
    private String createTM;

    public Book() {
    }

    public Book(Integer id, String name, String age, String description, String createTM) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
        this.createTM = createTM;
    }
}