package lee.repository;

import lee.domain.User;
import org.springframework.data.jpa.repository.Query;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 10:57
 */
public interface UserRepository extends Repository<User> {
    User findByUser(String user);
    @Query("select new User (u.userName,u.user) from User u")
    List<User> findUserSM();
}
