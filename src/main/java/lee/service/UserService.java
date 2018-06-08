package lee.service;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.User;
import lee.repository.UserRepository;
import lee.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author leon
 * @date 2018-06-08 16:51
 * @desc
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public ReturnObject addUser(User user) throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUser(user.getEmail()));
        if (optionalUser.isPresent()){
            return ReturnObject.re(Code.FAIL,"用户名已存在",null);
        }else {
            user.setLimits("user");
            user.setPassWord(MD5Utils.encode(user.getPassWord()));//md5加密
            userRepository.save(user);
            return ReturnObject.re(Code.OK,"用户添加成功",null);
        }
    }
    public ReturnObject login(User user){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUser(user.getEmail()));
        if (optionalUser.isPresent()){
            if (MD5Utils.verify(user.getPassWord(),optionalUser.get().getPassWord())){
                return ReturnObject.re(Code.OK,"登录成功",null);
            }else {
                return ReturnObject.re(Code.FAIL,"密码错误",null);
            }
        }else {
            return ReturnObject.re(Code.FAIL,"用户名不存在",null);
        }
    }
}
