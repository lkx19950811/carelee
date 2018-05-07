package lee.repository;

import lee.domain.Project;

import java.util.List;

/**
 * 项目仓库类
 */
public interface ProjectRepository extends Repository<Project>{
    List<Project> findByProjectName(String name);
}
