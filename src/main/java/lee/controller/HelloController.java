package lee.controller;

import lee.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 5:05
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @RequestMapping("/hello")
    public String hello(){
        return helloService.hello();
    }
    @RequestMapping("/json")
    @ResponseBody
    public Map json(){
        return helloService.json();
    }
    @RequestMapping({"/",""})
    public ModelAndView index(){
        return new ModelAndView("/admin/login");
    }

}
