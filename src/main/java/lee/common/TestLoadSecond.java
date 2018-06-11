//package lee.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
///**
// * @author leo
// * @date 2018-04-17 11:50
// * @description:
// */
//@Service
//@DependsOn({"first"})
//public class TestLoadSecond {
//    @Resource(name = "first")
//    private TestLoadFirst testLoadFirst;
//    public TestLoadSecond() {//构造方法
//        System.out.println("..................................Second Constructor");
//    }
//    @PostConstruct
//    public void init() {
//        System.out.println("..............................second init");
//    }
//}
