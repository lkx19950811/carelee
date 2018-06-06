package lee.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;

/**
 * 描述:
 * 用户注册信息类
 *
 * @author Leo
 * @create 2017-12-17 下午 8:30
 */
@Entity
public class User extends Domain{
    /**
     * 用户名
     */
    @JsonProperty("username")
    private String userName;
    /**
     * 用户姓名
     */
    private String user;
    /**
     * 用户密码
     */
    private String passWord;

    public User() {
    }

    public User(String userName, String user) {
        this.userName = userName;
        this.user = user;
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
