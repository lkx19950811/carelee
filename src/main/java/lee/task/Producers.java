//package lee.task;
//
//import lee.detail.Student;
//import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * 描述:
// * 生产者
// *
// * @author Leo
// * @create 2018-03-13 上午 2:08
// */
//@Service
//public class Producers {
//
//    @Resource(name = "msgMessageTemplate")
//    RabbitMessagingTemplate msgMessageTemplate;
//
//    public void send(Student student) {
//        System.out.println("send start.....");
//        msgMessageTemplate.convertAndSend("default.topic", "test2.send", student);
//    }
//}
