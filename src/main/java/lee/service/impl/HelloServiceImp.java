package lee.service.impl;

import lee.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @outhor Leo
 * @create 2017-12-17 下午 5:06
 */
@Service
public class HelloServiceImp implements HelloService {

    @Override
    public String hello() {

        return "HelloWorld this is lkx's project";
    }

    @Override
    public Map json() {
        Map<String,Object> returnMap = new HashMap<String, Object>();
        returnMap.put("name","lkx");
        returnMap.put("sex","man");
        return returnMap;
    }
}
