package com.example.bookstore;

public class Bill {
    int oid;
    int uid;
    int bid;
    String time;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bill(int oid, int uid, int bid, String time) {
        this.oid = oid;
        this.uid = uid;
        this.bid = bid;
        this.time = time;
    }

    public Bill() {
    }
}
