import lee.domain.User;
import lee.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 11:02
 */
public class UserRepositoryTest extends TestBasic {
    @Autowired
    UserRepository userRepository;
    @Test
    public void userFind(){
        User user = new User();
        user.setUser("lkx");
        user.setPassWord("lkx");
        user.setUserName("李凯鑫");
        if (user.equals(userRepository.findByUser("lkx"))){
            System.out.println("数据库中已有该用户");
            return;
        }
        userRepository.save(user);
    }
}
