package cn.sdust.service;

import cn.sdust.pojo.Student;

import java.util.List;

public interface IStudentService {
    void addStudent(Student student);
    void deleteStudent(Integer id);
    void updateInfo(Student student);
    List<Student> queryAll();
    List<Student> queryByPojo(Student student);
    Student queryById(Integer integer);
}
