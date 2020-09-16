package com.rabbitmq.entity;


//使用SpringData的时候，它需要在实体类中设置indexName 和type ，如果和传统型数据库比较的话，就相当于库和表。
// 需要注意的是indexName和type都必须是小写!!!
//@Document(indexName = "bookindex" ,type = "book")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTM() {
        return createTM;
    }

    public void setCreateTM(String createTM) {
        this.createTM = createTM;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", description='" + description + '\'' +
                ", createTM='" + createTM + '\'' +
                '}';
    }
}