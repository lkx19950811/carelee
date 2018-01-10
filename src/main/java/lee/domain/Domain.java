package lee.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 表的基类
 *
 * @author Leo
 * @create 2017-12-17 下午 10:34
 */
@MappedSuperclass
public class Domain {
    /**
     * 条目id,数据库主键，自动赋值，不需要程序员手工赋值
     */
    @Id
    @GeneratedValue(generator = "sequenceStyleGenerator")
    @GenericGenerator(name = "sequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "YC_ID_SEQUENCE"), @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
            @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"), @Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled") })
    private Long id;

    /**
     * 审计日志，记录条目创建时间，自动赋值，不需要程序员手工赋值
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /*
         * (non-Javadoc)
         *
         * @see org.springframework.data.domain.Persistable#isNew()
         */
    public boolean isNew() {
        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Auditable#getCreatedDate()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

}
