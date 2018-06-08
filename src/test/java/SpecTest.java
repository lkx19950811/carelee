import lee.domain.Task;
import lee.domain.spec.TaskSpec;
import lee.repository.TaskRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 描述:
 * 复杂查询测试
 *
 * @author Leo
 * @create 2018-03-25 上午 3:18
 */
public class SpecTest extends TestBasic{
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ApplicationContext context;
    @Test
    public void test1(){
        //查询出小目标
        List<Task> list = taskRepository.findAll(TaskSpec.findByFather());
        System.out.println(list);
    }
    @Test
    public void test2(){
        List<Task> list = taskRepository.findAll(TaskSpec.findProject());
        System.out.println(list);
    }
//    @Test
//    public void test(){
//        Object o = context.getBean("project");
//        Object o2 = context.getBean("pp");
//        Object o3 = context.getBean("Project");
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字");
        String s = scanner.nextLine();
        if (Pattern.matches("[0-9]+",s)){
            System.out.println("输出数字:" + s);
        }else {
            System.out.println("输入有误,请输入数字");
        }
    }
    @Test
    public void test3(){
        char[] a = {1,2,3};
        String s = "23";
        int i = s.indexOf(23);
        System.out.println(i);
}
}
