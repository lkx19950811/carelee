//package lee.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author leo
// * @date 2018-04-17 11:49
// * @description:
// */
//@Service("first")
//public class TestLoadFirst {
//    @Autowired
//    private TestLoadSecond testLoadSecond;
//    private static String name;
//    private static int i = 0;
//    public TestLoadFirst() {
//        System.out.println("...........................................First Constructor");
//    }
//    @PostConstruct
//    public void init(){
//        System.out.println(".....................................Fisrst init" );
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
