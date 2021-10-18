package cn.sdust.controller;
import cn.sdust.dao.BookDao;
import cn.sdust.dao.BillDao;
import cn.sdust.dao.RemarkDao;
import cn.sdust.dao.UserDao;
import cn.sdust.pojo.*;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserDao userDao;

    @Resource
    private BookDao bookDao;

    @Resource
    private RemarkDao remarkDao;

    @Resource
    private BillDao billDao;

    @RequestMapping("/login")
    @ResponseBody
    public User Login(@RequestParam("username") String username,
                     @RequestParam("password") String password)
    {
        User user = userDao.selectUserByname(username);
        if(user.getPassword().equals(password))
        return user;
        else
        {
            User user1=null;
            return user1;
        }
    }
    @RequestMapping("/register")
    @ResponseBody
    public int Register(@RequestParam("username") String username,
                     @RequestParam("password") String password)
    {
        User user= new User();
        user.setUname(username);
        user.setPassword(password);
        user.setUmoney(0);
        user.setUtype(0);
        User user1 = userDao.selectUserByname(username);
        if(username==null)
            return 0;
        else {
            userDao.saveUser(user);
            return 1;
        }
    }

    @RequestMapping("/getbook")
    @ResponseBody
    public List<Book> getBook(@RequestParam("serchItem") String data)
    {
        //System.out.println(data);
        JSONObject js = JSONObject.parseObject(data);
        SerchItem si = JSONObject.toJavaObject(js,SerchItem.class);
        //System.out.println(si.getBookName());
        //System.out.println(si.getMaxValue());
        List<Book> list = bookDao.selectBookbyItem(si);
       // System.out.println(list.size());
        return list;
    }
    @RequestMapping("/updateqian")
    @ResponseBody
    public User getBook(@RequestParam("uid") String uid,
                        @RequestParam("qian") String qian)
    {
       User user = userDao.selectUserByid(Integer.parseInt(uid));
       userDao.UpdateQian(Integer.parseInt(uid),qian);
       user.setQian(qian);
       return user;
    }
    @RequestMapping("/getabook")
    @ResponseBody
    public Book getABook(@RequestParam("uid") String uid)
    {
        Book book = bookDao.selectBookbyId(Integer.parseInt(uid));
        return book;
    }


    @RequestMapping("/postremark")
    @ResponseBody
    public void PostRemark(@RequestParam("remark") String remark)
    {
        JSONObject js = JSONObject.parseObject(remark);
        Remark rr = JSONObject.toJavaObject(js,Remark.class);

        remarkDao.insertRemark(rr);
    }
    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(@RequestParam("uid") String uid)
    {
        User user = userDao.selectUserByid(Integer.parseInt(uid));
        return user;
    }
    @RequestMapping("/getping")
    @ResponseBody
    public List<Remark> getPing(@RequestParam("bid") String bid)
    {
        List<Remark> list = remarkDao.getRemarkById(Integer.parseInt(bid));
        return list;
    }
    @RequestMapping("/getding")
    @ResponseBody
    public List<Bill> getDing(@RequestParam("uid") String uid)
    {
        List<Bill> list = billDao.getBillByid(Integer.parseInt(uid));
        return list;
    }

    @RequestMapping("/insertbill")
    @ResponseBody
    public void insertBill(@RequestParam("bill") String data)
    {
        JSONObject js = JSONObject.parseObject(data);
        Bill order = JSONObject.toJavaObject(js, Bill.class);
        order.setTime(order.getTime().trim());
        billDao.insertBill(order);
    }
    @RequestMapping("/addxiao")
    @ResponseBody
    public void addXiao(@RequestParam("bid") String data)
    {
        int bid=Integer.parseInt(data);
        Book book = bookDao.selectBookbyId(bid);
        bookDao.addXiao(bid,book.getXiao()+1);
    }
    @RequestMapping("/jianqian")
    @ResponseBody
    public void jianQian(@RequestParam("uid") String data1,@RequestParam("money") String data2)
    {
        int uid=Integer.parseInt(data1);
        double money=Double.parseDouble(data2);
        User user = userDao.selectUserByid(uid);
        userDao.Qianjianjian(uid,user.getUmoney()-money);
    }
}
