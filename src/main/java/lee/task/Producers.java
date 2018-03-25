package lee.task;

import lee.detail.Student;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 描述:
 * 生产者
 *
 * @author Leo
 * @create 2018-03-13 上午 2:08
 */
@Service
public class Producers {

    @Autowired
    RabbitMessagingTemplate rabbitSendTemplate;

    public void send(Student student) {
        System.out.println("send start.....");
        rabbitSendTemplate.convertAndSend("default.topic", "test2.send", student);
    }
}
