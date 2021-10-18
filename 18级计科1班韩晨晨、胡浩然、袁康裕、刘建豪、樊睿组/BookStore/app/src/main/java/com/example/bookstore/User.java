package com.example.bookstore;
import java.io.Serializable;
public class User implements Serializable {
    private int uid;
    private String uname;
    private String upassword;
    private double umoney;
    private int utype;
    private String qian;

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getQian() {
        return qian;
    }

    public void setQian(String qian) {
        this.qian = qian;
    }

    public String getPassword() {
        return upassword;
    }

    public void setPassword(String password) {
        this.upassword = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getUmoney() {
        return umoney;
    }

    public void setUmoney(double umoney) {
        this.umoney = umoney;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public User() {
    }

    public User(int uid, String uname, String upassword, double umoney, int utype, String qian) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.umoney = umoney;
        this.utype = utype;
        this.qian = qian;
    }
}
