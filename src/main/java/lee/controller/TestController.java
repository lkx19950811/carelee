package lee.controller;

import com.alibaba.fastjson.JSONObject;
import lee.detail.Student;
import lee.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-13 上午 2:11
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
//
//    @Autowired
//    Producers producers;
//
//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    public String test() {
//        Student s = new Student();
//        s.setName("zhangsan");
//        s.setAddress("wuhan");
//        s.setAge(20);
//        producers.send(s);
//        return "已发送";
//    }
//    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
//    public String rest(@PathVariable("id")String id) {
//        return id;
//    }
//    @RequestMapping(value = "st",method = RequestMethod.POST)
//    public Student student(@RequestBody Student st) {
//        st.setName("农脑子瓦特了" + st.getName());
//        return st;
//    }
//
//    @RequestMapping(value = "/body",method = {RequestMethod.GET,RequestMethod.POST})
//    public Map body(@RequestBody Student student,HttpServletRequest request){
//        Map<String,String> rerturnmap = new HashMap<String, String>();
//        rerturnmap.put("name",student.getName());
//        rerturnmap.put("sometoSay",student.getAddress() + "是你家地址对吗?");
//        System.out.println("这是body");
//        System.out.println(request.getAttribute("lkx"));
//        return rerturnmap;
//    }
//    @ModelAttribute //该方法会在进入这个controller时就执行
//    public void init(HttpServletRequest request)
//    {
//        System.out.println(request);
//        request.setAttribute("lkx","lkx");
//        System.out.println("最先执行的方法");
//    }
    @RequestMapping("show")
    public String show(HttpServletRequest request){
        Map<String,String[]> map = request.getParameterMap();
        JSONObject json = DataHelper.getJsonFromREQ(request);
        if (map.size()==0){
            if (json!=null){
                return json.toJSONString();
            }else{
                return "没有东西!";
            }
        }else {
            StringBuilder sb = new StringBuilder();
            map.forEach((k,v)-> sb.append(k).append("=").append(v[0]).append("&"));
            return sb.toString();
        }
    }
}
