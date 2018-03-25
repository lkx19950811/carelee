package lee.detail;

import java.io.Serializable;

/**
 * 描述:
 * 学生类
 *
 * @author Leo
 * @create 2018-03-13 上午 2:06
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -5918016936767039419L;
    private String name;
    private Integer age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}