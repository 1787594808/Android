package cn.sdust.pojo;

public class Remark {
    int pid;
    int uid;
    int bid;
    String comment;

    public Remark() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Remark(int pid, int uid, int bid, String comment) {
        this.pid = pid;
        this.uid = uid;
        this.bid = bid;
        this.comment = comment;
    }
}
