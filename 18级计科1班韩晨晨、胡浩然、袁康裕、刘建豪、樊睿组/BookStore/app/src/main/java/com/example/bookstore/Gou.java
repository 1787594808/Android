package com.example.bookstore;

import org.litepal.crud.DataSupport;

public class Gou extends DataSupport {
    int bid;
    int uid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Gou() {
    }

    public Gou(int bid, int uid) {
        this.bid = bid;
        this.uid = uid;
    }
}
