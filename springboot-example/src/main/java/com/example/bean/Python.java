package com.example.bean;

import org.springframework.stereotype.Component;

@Component
public class Python {

    // 修改属性的可见性来限制对属性的访问（一般限制为`private`）
    private String ketWord;
    //URL地址
    private String shop;
    //商店名称
    private String name;
    //商业注册号
    private String enrollNumber;
    //业务类型
    private String shop_yw;
    //增值税
    private String shop_name2;
    //地址
    private String address;

    //对每个值属性提供对外的公共方法访问，即创建一对赋取值方法，用于对私有属性的访问


    public String getKetWord() {
        return ketWord;
    }

    public void setKetWord(String ketWord) {
        this.ketWord = ketWord;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public String getShop_yw() {
        return shop_yw;
    }

    public void setShop_yw(String shop_yw) {
        this.shop_yw = shop_yw;
    }

    public String getShop_name2() {
        return shop_name2;
    }

    public void setShop_name2(String shop_name2) {
        this.shop_name2 = shop_name2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Python() {
    }

    public Python(String ketWord, String shop, String name, String enrollNumber, String shop_yw, String shop_name2, String address) {
        this.ketWord = ketWord;
        this.shop = shop;
        this.name = name;
        this.enrollNumber = enrollNumber;
        this.shop_yw = shop_yw;
        this.shop_name2 = shop_name2;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Python{" +
                "ketWord='" + ketWord + '\'' +
                ", shop='" + shop + '\'' +
                ", name='" + name + '\'' +
                ", enrollNumber='" + enrollNumber + '\'' +
                ", shop_yw='" + shop_yw + '\'' +
                ", shop_name2='" + shop_name2 + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}