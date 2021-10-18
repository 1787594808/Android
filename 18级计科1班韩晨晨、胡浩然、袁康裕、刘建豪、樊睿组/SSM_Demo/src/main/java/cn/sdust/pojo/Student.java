package cn.sdust.pojo;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;
    String tele;
    public Student(int id,String name,String tele)
    {
        super();
        this.id=id;
        this.name=name;
        this.tele=tele;
    }
    public Student()
    {
        super();
    }
    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
