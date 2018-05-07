import lee.detail.Student;
import lee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-13 下午 11:23
 */
public class JedisTest extends TestBasic{ //redisTemplate测试类
    private static int sum = 1;
    private Logger logger = LoggerFactory.getLogger(JedisTest.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate") //神奇吗 这个
    /**
     * 是不是很奇怪，毫无父子兄弟关系的两个类，怎么能互相注入呢？这个是Spring的Editor机制，
     * 搜索下ValueOperationsEditor这个类就知道了，Spring在注入的时候调用了Editor的setValue方法。
     */
    private ValueOperations<String,Student> valueOperations;

    /**
     * redis提供了分片api,通过hash一致性算法,将值均匀分布在节点内
     * 缺点:节点分布不均匀,系统健壮性较差
     */
    @Test
    public void jedisTest() { //redis 分片
        List<JedisShardInfo> listinfo = new ArrayList<JedisShardInfo>();
        JedisShardInfo je1 = new JedisShardInfo("192.168.246.130",6379); //这是我自己的redis,可以在本地开多个reids,只需要多份配置文件即可
        JedisShardInfo je2 = new JedisShardInfo("192.168.246.131",6379);
        JedisShardInfo je3 = new JedisShardInfo("192.168.246.131",6380);
        listinfo.add(je1);
        listinfo.add(je2);
        listinfo.add(je3);
        ShardedJedis jedis = new ShardedJedis(listinfo);
        for (int i=0;i<100;i++) {
            jedis.set(i + "key",i + "value");
        }
    }

    /**
     * 存入student对象
     */
    @Test
    public void redisTemp(){
        Student student = new Student();
        student.setName("lkx56");
        redisTemplate.opsForValue().set("13245",student);
        Student s = (Student) redisTemplate.opsForValue().get("13245");
        System.out.println(s.getName());
    }

    /**
     * 存入student对象,并设置失效时间
     * @throws InterruptedException
     */
    @Test
    public void valueOp() throws InterruptedException {
        Student student = new Student();
        student.setName("lkx");
        valueOperations.set("xxx",student);
        redisTemplate.expire("xxx",15,TimeUnit.SECONDS);//设置
        System.out.println(".........................xxx15秒后失效");
        System.out.println(valueOperations.get("xxx"));
        Thread.sleep(15100);
        System.out.println(valueOperations.get("xxx"));
    }

    /**
     * redis  list测试 list api集合
     */
    @Test
    public void ListTest(){
        ListOperations<String,String> vo = redisTemplate.opsForList();
        vo.leftPush("book", "wzg"); // 左进栈..........1
        Log(vo.range("book",0,-1)); // 0,-1返回全部 -1是从后往前数 同理-2就是倒数第二个
        vo.leftPushAll("book", "cff","cl","mc"); // 左全部进栈.............2
        Log(vo.range("book",0,-1));
        vo.leftPush("book", "cl", "||"); // 以 "cl" 为中心,左进栈............3
        Log(vo.range("book",0,-1));
        vo.rightPush("book", "yw"); // 右进栈................4
        Log(vo.range("book",0,-1));
        vo.rightPushAll("book", "sj","jy"); // 右全部进栈.............5
        Log(vo.range("book",0,-1));
        vo.rightPush( "book", "sj", "||"); // 在sj的右边进栈..............6
        Log(vo.range("book",0,-1));
        vo.leftPop("book"); // ......................7
        Log(vo.range("book",0,-1));
        vo.rightPop("book"); // ......................8
        Log(vo.range("book",0,-1));
        vo.rightPopAndLeftPush("book", "bag");// ..............9,10
        Log(vo.range("book",0,-1));
        Log(vo.range("bag",0,-1));
        vo.set("book", 0, "gzf"); // .................11
        Log(vo.range("book",0,-1));
        vo.leftPush("bag", "||"); // .....................12
        Log(vo.range("bag",0,-1));
        vo.remove("bag", 3, "||");// ..........13
        Log(vo.range("bag",0,-1));
        redisTemplate.delete("book");
        redisTemplate.delete("bag");
    }
    private void Log(Object o){
        logger.info(sum + ":......." +  String.valueOf(o));
        sum++;
    }
}
