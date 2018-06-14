package lee.domain;

import javax.persistence.Entity;

/**
 * @author leon
 * @date 2018-06-14 17:45
 * @desc 回收站
 */
@Entity
public class Recycle extends Domain{
    private String classType;
    private String status;
    private String delId;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelId() {
        return delId;
    }

    public void setDelId(String delId) {
        this.delId = delId;
    }
}
