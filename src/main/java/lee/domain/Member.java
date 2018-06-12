package lee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

/**
 * @author leon
 * @date 2018-06-11 15:17
 * @desc 会员
 */
@Entity
@JsonIgnoreProperties(value = "true")
public class Member extends Domain {
    /**
     * email
     */
    private String email;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     *状态
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
