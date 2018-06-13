package lee.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 * echarts控制器
 *
 * @author Leo
 * @create 2018-06-14 上午 12:37
 */
@RestController
@RequestMapping("echarts")
public class EchartsController {
    @RequestMapping("/{id}")
    public ModelAndView echarts(@PathVariable("id")String id){
        ModelAndView modelAndView = new ModelAndView("/admin/echarts"+id);
        return modelAndView;
    }
}
