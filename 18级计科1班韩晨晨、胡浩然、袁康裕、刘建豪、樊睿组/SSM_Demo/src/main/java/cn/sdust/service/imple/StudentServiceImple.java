package cn.sdust.service.imple;

import cn.sdust.dao.StudentDao;
import cn.sdust.dao.UserDao;
import cn.sdust.pojo.Student;
import cn.sdust.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImple implements IStudentService {
    @Resource
    private StudentDao studentDao;
    @Override
    public Student queryById(Integer id) {
        return this.studentDao.selectStudentByid(id);
    }

    @Override
    public void addStudent(Student student) {
        this.studentDao.saveStudent(student);
    }
    @Override
    public void deleteStudent(Integer id) {
        this.studentDao.deleteStudent(id);
    }

    @Override
    public void updateInfo(Student student) {
        this.studentDao.updateStudent(student);
    }

    @Override
    public List<Student> queryAll() {
        return this.studentDao.selectStudent();
    }


    @Override
    public List<Student> queryByPojo(Student student) {
        return this.studentDao.selectStudentByPojo(student);
    }
}
