package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.User;
import lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author leon
 * @date 2018-06-08 16:20
 * @desc
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(@Param("user") String user,@Param("password") String password){
        ReturnObject object = userService.login(new User(user,password));
        if (object.equals(Code.OK)){
            return new ModelAndView("forward:/index.html");
        }
        return new ModelAndView("forward:/login.html");
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ReturnObject add(@Param("user") String user,@Param("password") String password,@Param("username")String username) throws Exception {
        User u = new User(user,password);
        u.setUserName(username);
        return userService.addUser(u);
    }
}
