package lee.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-26 上午 1:06
 */
@Entity
public class Project {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @OneToMany(mappedBy = "project")
    private Set<Task> tasks = new HashSet<Task>();;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
