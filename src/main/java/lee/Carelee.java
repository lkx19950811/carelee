package lee;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 4:44
 */
@SpringBootApplication
@EnableScheduling
public class Carelee extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(Carelee.class);
    @Value("${server.additional-ports}")
    String ports;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(Carelee.class);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        if (ports != null && !StringUtils.isEmpty(ports)) {
            // 判断如果是Tomcat才进行如下配置
            if (configurableEmbeddedServletContainer instanceof TomcatEmbeddedServletContainerFactory) {
                // 转类型为TomcatEmbeddedServletContainerFactory
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer;

                String[] portsArray = ports.split(",");
                for (String portStr : portsArray) {
                    int port = Integer.parseInt(portStr);
                    // Tomcat中，一个Connecter监听一个端口
                    // 指定协议为HTTP/1.1
                    Connector httpConnector = new Connector("HTTP/1.1");
                    httpConnector.setPort(port);
                    tomcat.addAdditionalTomcatConnectors(httpConnector);
                }
            }
        }
    }
}
