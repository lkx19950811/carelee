package lee.controller;

import lee.detail.Student;
import lee.task.Producers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-13 上午 2:11
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    Producers producers;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String test() {
        Student s = new Student();
        s.setName("zhangsan");
        s.setAddress("wuhan");
        s.setAge(20);
        producers.send(s);
        return "已发送";
    }
    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
    public String rest(@PathVariable("id")String id) {
        return id;
    }

}
