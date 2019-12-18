package com.example.toolcabinet;

import java.io.Serializable;

//vo：value object
public class Music implements Serializable {
    private int img;
    private String name1, name2;
    //生成get/set方法

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    //创建无参和有参的构造方法：alter+insert

    public Music(int img, String name1, String name2) {
        this.img = img;
        this.name1 = name1;
        this.name2 = name2;
    }


    //重写toString()方法：alter+insert

    @Override
    public String toString() {
        return "Music{" +
                "img=" + img +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
}
