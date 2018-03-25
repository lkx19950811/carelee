package lee.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-25 上午 2:41
 */
@Entity
@Table(name = "tb_task")
public class Task {

    private Long id ;
    private String taskName ;
    private Date createTime ;
    private String taskDetail ;
    private String fatherId;
    private Project project;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Column(name = "task_detail")
    public String getTaskDetail() {
        return taskDetail;
    }
    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }
    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", createTime=" + createTime +
                ", taskDetail='" + taskDetail + '\'' +
                ", fatherId='" + fatherId + '\'' +
                '}';
    }
}
