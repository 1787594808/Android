package cn.sdust.pojo;

import java.io.Serializable;

public class Book implements Serializable {
    private int bid;
    private String bname;
    private String bwriter;
    private String bdetail;
    private double bvalue;
    private int xiao;
    private String btype;

    public int getXiao() {
        return xiao;
    }

    public void setXiao(int xiao) {
        this.xiao = xiao;
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

    public Book(int bid, String bname, String bwriter, String bdetail, double bvalue, int xiao, String btype) {
        this.bid = bid;
        this.bname = bname;
        this.bwriter = bwriter;
        this.bdetail = bdetail;
        this.bvalue = bvalue;
        this.xiao = xiao;
        this.btype = btype;
    }
}

