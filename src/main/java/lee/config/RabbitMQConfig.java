package lee.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2017/6/1 11:26.
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 注入配置文件属性
     */
    @Value("${spring.rabbitmq.addresses}")
    String addresses;//MQ地址
    @Value("${spring.rabbitmq.username}")
    String username;//MQ登录名
    @Value("${spring.rabbitmq.password}")
    String password;//MQ登录密码
    @Value("${spring.rabbitmq.virtual-host}")
    String vHost;//MQ的虚拟主机名


    /**
     * 创建 ConnectionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        return RabbitUtil.connectionFactory(addresses, username, password, vHost);
    }

    /**
     * 创建 RabbitAdmin
     *
     * @param connectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) throws Exception {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }
    @Bean
    @Lazy( value = true)
    public Project project(){
        Project project = new Project();
        System.out.println("project");
        return project;
    }
    @Bean
    @Lazy(value = true)
    public Project Project(){
        Project project = new Project();
        System.out.println("Project");
        return project;
    }
    @Bean(name = "pp")
    @Lazy(value = true)
    public Project pproject(){
        Project project = new Project();
        System.out.println("pp");
        return project;
    }

}