package lee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author leon
 * @date 2018-06-14 17:45
 * @desc 回收站
 */
@Entity
public class Recycle extends Domain{
    @Column(name = "memberId")
    private String memberId;
    @Column(name = "commentId")
    private String comment;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Recycle{" +
                "memberId='" + memberId + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
