package cn.net.sdkd.activitytest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/9/28.
 */

public class Person implements Parcelable{
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        /* 写对象：按照顺序写对象的各个属性值*/
        parcel.writeString(name);
        parcel.writeInt(age);

    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
        @Override
        public Person createFromParcel(Parcel parcel) {
            /* 从Parcel中读取对象的属性值，返回对象 */
            Person person = new Person();
            person.name = parcel.readString();
            person.age = parcel.readInt();
            return person;
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[i];
        }
    };

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}