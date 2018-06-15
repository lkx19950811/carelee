package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.User;
import lee.service.CommentsService;
import lee.service.MemberService;
import lee.service.MovieService;
import lee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leon
 * @date 2018-06-08 16:20
 * @desc  管理员的一系列接口
 *        使用表单提交无法解析成类,所以只能用@param了
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 登录接口
     */
    @Autowired
    UserService userService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    MovieService movieService;
    @Autowired
    MemberService memberService;
    @Value("${size}")
    private String pagesize;
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(@Param("user") String user, @Param("password") String password, HttpServletRequest request){
        ReturnObject object = userService.login(new User(user,password));
        if (object.getCode().equals(Code.OK)){
            //登录时设置session
            request.getSession().setAttribute("user",object.getObject());
            ModelAndView modelAndView = new ModelAndView("/admin/index");
            modelAndView.addObject("user",(User)object.getObject());
            modelAndView.addObject("pagesize",pagesize);
            logger.debug(object.getMessage());
            return modelAndView;
        }
        logger.debug(object.getMessage());
        return new ModelAndView("/admin/login");
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject add(@Param("user") String user,@Param("password") String password,@Param("username")String username) throws Exception {
        User u = new User(user,password);
        u.setUserName(username);
        return userService.addUser(u);
    }
    @RequestMapping("welcome")
    public ModelAndView welcome(HttpServletRequest request){
        Long countComment = commentsService.CountComment();//评论计数
        Long countMovie = movieService.countMovie();
        Long countUser = memberService.countMember();
        User user =  (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("/admin/welcome");
        modelAndView.addObject("countComment",countComment);
        modelAndView.addObject("countMovie",countMovie);
        modelAndView.addObject("countUser",countUser);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping("adminList")
    public ModelAndView adminList(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin-list");
        return modelAndView;
    }
}
