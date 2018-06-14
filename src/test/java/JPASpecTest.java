import lee.domain.Project;
import lee.domain.Task;
import lee.domain.spec.TaskSpec;
import lee.repository.CommentRepository;
import lee.repository.MemberRepository;
import lee.repository.ProjectRepository;
import lee.repository.TaskRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * JPA复杂查询测试
 */
public class JPASpecTest extends TestBasic {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    Project project;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void test (){
        List<Task> list = taskRepository.findAll(TaskSpec.findByFather());
        System.out.println(list);
    }
    @Test
    public void test1() {
        List<Task> list = taskRepository.findByProjectName("测试");
        System.out.println(list);
    }
    @Test
    public void test2(){
        Page<Project> page = projectRepository.findAll(new PageRequest(1,2));
        List<Project> list = page.getContent();
        System.out.println("totalNum" + page.getTotalElements());
        System.out.println(list);
    }
    @Test
    public void test3(){
        System.out.println("项目名字" + project.getProjectName());
    }

    public static void main(String[] args){

    }
    @Test
    public void test4(){
        System.out.println(projectRepository.findByProjectName("first"));
    }
    @Test
    public void test5(){
        System.out.println(commentRepository.count());
    }
    @Test
    public void test6(){
//        System.out.println(memberRepository.findUndelMember());
    }

}
