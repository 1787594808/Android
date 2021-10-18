package cn.sdust.service;
import cn.sdust.pojo.User;
import java.util.List;

public interface IUserService {
    void addUser(User user);
    List<User> queryAll();
    void deleteUer(User user);
}
