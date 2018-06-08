package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.User;
import lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leon
 * @date 2018-06-08 16:20
 * @desc 使用表单提交无法解析成类,所以只能用@param了
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    /**
     * 登录接口
     */
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@Param("user") String user, @Param("password") String password, HttpServletRequest request){
        ReturnObject object = userService.login(new User(user,password));
        if (object.getCode().equals(Code.OK)){
            //登录时设置session
            request.getSession().setAttribute("user",user);
            return "redirect:/index.html";
        }
        return "redirect:/login.html";
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject add(@Param("user") String user,@Param("password") String password,@Param("username")String username) throws Exception {
        User u = new User(user,password);
        u.setUserName(username);
        return userService.addUser(u);
    }
}
