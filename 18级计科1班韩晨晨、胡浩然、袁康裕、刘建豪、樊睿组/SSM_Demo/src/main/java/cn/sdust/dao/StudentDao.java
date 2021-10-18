package cn.sdust.dao;

import cn.sdust.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public interface StudentDao {
    List<Student> selectStudent();
    List<Student> selectStudentByPojo(Student student);
    Student selectStudentByid(@Param(value="id") Integer id);
    Student selectStudentByname(String username);
    int saveStudent(Student student);
    int updateStudent(Student student);
    int deleteStudent(@Param(value="id") Integer id);

}
