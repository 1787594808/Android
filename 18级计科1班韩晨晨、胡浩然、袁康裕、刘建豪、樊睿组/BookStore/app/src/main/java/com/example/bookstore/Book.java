package com.example.bookstore;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Book implements Serializable {
    private int bid;
    private String bname;
    private String bwriter;
    private String bdetail;
    private double bvalue;
    private int xiao;
    private String btype;
    private Bitmap pic1;
    private Bitmap pic2;
    private Bitmap pic3;
    public int getXiao() {
        return xiao;
    }

    public void setXiao(int xiao) {
        this.xiao = xiao;
    }


    public Bitmap getPic1() {
        return pic1;
    }
    public void setPic1(Bitmap pic1) {
        this.pic1 = pic1;
    }
    public Bitmap getPic2() {
        return pic2;
    }
    public void setPic2(Bitmap pic2) {
        this.pic2 = pic2;
    }
    public Bitmap getPic3() {
        return pic3;
    }
    public void setPic3(Bitmap pic3) {
        this.pic3 = pic3;
    }
    public Book() {
    }
    public int getBid() {
        return bid;
    }
    public void setBid(int bid) {
        this.bid = bid;
    }
    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBwriter() {
        return bwriter;
    }
    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }
    public String getBdetail() {
        return bdetail;
    }
    public void setBdetail(String bdetail) {
        this.bdetail = bdetail;
    }

    public double getBvalue() {
        return bvalue;
    }

    public void setBvalue(double bvalue) {
        this.bvalue = bvalue;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

//    public Book(int bid, String bname, String bwriter, String bdetail, double bvalue, int xiao, String btype, Bitmap pic1, Bitmap pic2, Bitmap pic3) {
//        this.bid = bid;
//        this.bname = bname;
//        this.bwriter = bwriter;
//        this.bdetail = bdetail;

    public Book(int bid, String bname, String bwriter, String bdetail, double bvalue, int xiao, String btype) {
        this.bid = bid;
        this.bname = bname;
        this.bwriter = bwriter;
        this.bdetail = bdetail;
        this.bvalue = bvalue;
        this.xiao = xiao;
        this.btype = btype;
    }
//        this.bvalue = bvalue;
//        this.xiao = xiao;
//        this.btype = btype;
//        this.pic1 = pic1;
//        this.pic2 = pic2;
//        this.pic3 = pic3;
//    }
}

