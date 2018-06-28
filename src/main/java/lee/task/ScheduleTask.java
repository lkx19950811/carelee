package lee.task;

import lee.domain.Member;
import lee.service.MemberService;
import lee.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

/**
 * @author leo
 * 定时任务测试
 */
@Component
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    MemberService memberService;
    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

//    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
//    public void testFixDelay() throws InterruptedException {
//        logger.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
//    }
//
//    @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
//    public void testFixedRate() {
//        logger.info("===fixedRate: 第{}次执行方法", fixedRateCount++);
//    }
//
//    @Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
//    public void testInitialDelay() {
//        logger.info("===initialDelay: 第{}次执行方法", initialDelayCount++);
//    }

//    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void testCron() throws Exception {
        String name = RandomMock.makeName();
        Member member = new Member();
        member.setName(name);
        member.setEmail(RandomMock.maekEmail());
        member.setPassword(MD5Utils.encode("123456"));
        if (Math.random()>0.5){
            member.setStatus("启用");
        }else {
            member.setStatus("停用");
        }
        memberService.saveMember(member);
        logger.info("===自动插入名字线程: 第{}次执行方法,用户名:{}", cronCount++,member.getName());
    }
}
