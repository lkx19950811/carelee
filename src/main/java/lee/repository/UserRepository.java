package lee.repository;

import lee.domain.User;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 10:57
 */
public interface UserRepository extends Repository<User> {
    User findByUser(String user);
}
