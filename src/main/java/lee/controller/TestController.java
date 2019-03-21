package lee.controller;

import com.alibaba.fastjson.JSONObject;
import com.huifu.saturn.cfca.CFCASignature;
import com.huifu.saturn.cfca.VerifyResult;
import lee.detail.Student;
import lee.utils.DataHelper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.nio.charset.Charset;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());//
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
    @RequestMapping(value = "show",produces = MediaType.TEXT_PLAIN_VALUE)
    public String show(HttpServletRequest request) throws Exception {
        Map<String,String[]> map = request.getParameterMap();
        String str = DataHelper.getStrFromREQ(request);
        JSONObject json = null;
        String xml = null;
        try {
            json = JSONObject.parseObject(str);
        }catch (Exception e){
            xml = str;
        }
        if (map.size()==0){
            logger.debug("在body中的参数:{}",str);
            if (json!=null){
                return "SUCCESS";
            }else if (xml!=null){
                return "SUCCESS";
            }else {
                return "nothing";
            }
        }else {
            StringBuilder sb = new StringBuilder();
            map.forEach((k,v)-> sb.append(k).append("=").append(v[0]).append("&"));
            logger.info("使用 参数{}",sb.toString());
            if (request.getParameter("check_value")!=null){
                JSONObject jsonObject = JSONObject.parseObject(
                        parseResult(request.getParameter("check_value"),"/home/key/huifu/CFCA_ACS_OCA31.cer"));
                logger.info(jsonObject.toJSONString());
                logger.info("RECV_ORD_ID_" + jsonObject.getString("order_id"));
                logger.info(jsonObject.toJSONString());
                return "RECV_ORD_ID_" + jsonObject.getString("order_id");
            }
            return "SUCCESS";
        }
    }
    private String parseResult(String responseJson, String key) throws Exception {
        // 解签用的证书，请换成商户自己下载的证书
        VerifyResult verifyResult = CFCASignature.verifyMerSign("100001", responseJson, "utf-8", key);
        if ("000".equals(verifyResult.getCode())) {
            String content = new String(verifyResult.getContent(), Charset.forName("utf-8"));
            return new String(Base64.decodeBase64(content), Charset.forName("utf-8"));
        } else {
            return "验签失败";
        }
    }
}
