package lee.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

import javax.persistence.Entity;

/**
 * 描述:
 * 管理员
 *
 * @author Leo
 * @create 2017-12-17 下午 8:30
 */
@Entity
public class User extends Domain{
    /**
     * 昵称
     */
    @JsonProperty("username")
    private String userName;
    /**
     * 用户名
     */
    private String user;
    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 权限
     */
    private String limits;

    /**
     * 手机
     */
    private String mobilephone;

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public User() {
    }

    public User(String email,String passWord) {
        this.passWord = passWord;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user1 = (User) o;

        return user != null ? user.equals(user1.user) : user1.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", user='" + user + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
