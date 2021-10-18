package cn.sdust.controller;
import cn.sdust.pojo.Student;
import cn.sdust.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {
    @Resource
    private IStudentService studentService;
    @RequestMapping("/student")
    public ModelAndView intoSlist(ModelAndView mv)
    {
        List<Student> students = studentService.queryAll();
        mv.addObject("students", students);
        mv.setViewName("studentlist");
        return mv;
    }
    @RequestMapping("/student/studentDetail")
    public ModelAndView showStudent(@RequestParam("id")int id,ModelAndView mv)
    {
        Student student = studentService.queryById(id);
        mv.addObject("student",student);
        mv.setViewName("detile");
        return mv;
    }
    @RequestMapping("/student/toDeleteStudent")
    public ModelAndView deleteStudent(@RequestParam("id")int id,ModelAndView mv)
    {
        studentService.deleteStudent(id);
        mv.setViewName("redirect:/student");
        return mv;
    }
    @RequestMapping("/student/toadd")
    public ModelAndView addStudent(ModelAndView mv)
    {
        mv.setViewName("addstudent");
        return mv;
    }
    @RequestMapping("/student/save")
    public ModelAndView saveStudent(
            @RequestParam("id") String id,
            @RequestParam("name")String name, @RequestParam("tele") String tele,ModelAndView mv)
    {
        if(id=="")
        {
            Student student = new Student();
            student.setName(name);
            student.setTele(tele);
            studentService.addStudent(student);
        }
        else
        {
            Student student = new Student();
            student.setId(Integer.parseInt(id));
            student.setName(name);
            student.setTele(tele);
            studentService.updateInfo(student);
        }
        mv.setViewName("redirect:/student");
        return mv;
    }
    @RequestMapping("/student/serch")
    public ModelAndView serchStudent(
            @RequestParam("name")String name, @RequestParam("tele") String tele,ModelAndView mv)
    {
        Student student = new Student();
        student.setName(name);
        student.setTele(tele);
        System.out.println(name);
        List<Student> students= studentService.queryByPojo(student);
        System.out.println(students.size());
        mv.addObject("students",students);
        mv.setViewName("serchresult");
        return mv;
    }
    @RequestMapping("/student/toEditStudent")
    public ModelAndView studentEdit(@RequestParam("id") int id,ModelAndView mv){
        Student student = studentService.queryById(id);
        mv.addObject("student",student);
        mv.setViewName("studentupdate");
        return mv;
    }
}
