package cn.sdust.service.imple;

import cn.sdust.dao.UserDao;
import cn.sdust.pojo.User;
import cn.sdust.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImple implements IUserService {
    @Resource
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> queryAll() {
        return userDao.selectUser();
    }

    @Override
    public void deleteUer(User user) {
        userDao.deleteUser(user);
    }


}