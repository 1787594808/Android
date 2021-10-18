package cn.sdust.dao;
import cn.sdust.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao {
    List<User> selectUser();
    User selectUserByid(@Param(value="uid") Integer id);
    User selectUserByname(@Param(value="uname") String name);
    int saveUser(User user);
    int deleteUser(User user);
    void UpdateQian(@Param(value="uid") Integer id,@Param(value="qian") String qian);
    void Qianjianjian(@Param(value="uid") Integer id,@Param(value="money") Double money);
}