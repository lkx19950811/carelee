package lee.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoadSeed {
    @Value("${http}")
    private String http;

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }
}
