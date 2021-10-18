import cn.sdust.pojo.User;
import cn.sdust.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserTest {
    @Resource
    IUserService userService;
    @Test
    public void getAll() {
        int a=1;
//        User users = userService.queryById(a);
//            System.out.println(users.getUsername());
    }
}