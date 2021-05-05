package com.spring.springutil.entity;

import java.io.Serializable;

public class Season implements Serializable {

    private Integer Id;
    private String Username;
    private String Password;
    private String Spring;
    private String Summer;
    private String Autumn;
    private String Winter;

    public Season() {
    }

    public Season(Integer id, String username, String password, String spring, String summer, String autumn, String winter) {
        Id = id;
        Username = username;
        Password = password;
        Spring = spring;
        Summer = summer;
        Autumn = autumn;
        Winter = winter;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSpring() {
        return Spring;
    }

    public void setSpring(String spring) {
        Spring = spring;
    }

    public String getSummer() {
        return Summer;
    }

    public void setSummer(String summer) {
        Summer = summer;
    }

    public String getAutumn() {
        return Autumn;
    }

    public void setAutumn(String autumn) {
        Autumn = autumn;
    }

    public String getWinter() {
        return Winter;
    }

    public void setWinter(String winter) {
        Winter = winter;
    }
}
