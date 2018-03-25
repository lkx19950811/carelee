package lee.task;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * 描述:
 * 消费者类
 *
 * @author Leo
 * @create 2018-03-13 上午 2:07
 */
@Service
public class Consumers {

    @RabbitListener(
            //1.rabbitAdmin:RabbitAdmin名称
            admin = "rabbitAdmin",
            bindings = @QueueBinding(
                    //1.test.demo.send:队列名,2.true:是否长期有效,3.false:是否自动删除
                    value = @Queue(value = "test.demo.send", durable = "true", autoDelete = "false"),
                    //1.default.topic交换器名称(默认值),2.true:是否长期有效,3.topic:类型是topic
                    exchange = @Exchange(value = "default.topic", durable = "true", type = "topic"),
                    //test2.send:路由的名称,ProducerConfig 里面 绑定的路由名称(xxxx.to(exchange).with("test2.send")))
                    key = "test2.send")
    )
    public void test(Message message) {
        System.out.println("receive....");
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(message.getBody());
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
            System.out.println("message:" + obj.toString());
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }

    }
}
