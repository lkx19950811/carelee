package lee.repository;

import lee.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述: 测试jpa复杂查询
 *
 * @author Leo
 * @create 2018-03-25 上午 2:51
 */
public interface TaskRepository extends Repository<Task> {
    @Query("select t from Task t inner join t.project as p where p.projectName like %:name%")
    List<Task> findByProjectName(@Param(value = "name")String name);
}
