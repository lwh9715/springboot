package com.jpa.bean;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "user_jpa")//增加此语句来指定所映射的表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
