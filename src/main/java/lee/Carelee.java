package lee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 描述:
 *
 * @author Leo
 * @create 2017-12-17 下午 4:44
 */
@SpringBootApplication
public class Carelee extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(Carelee.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(Carelee.class);
    }
}
