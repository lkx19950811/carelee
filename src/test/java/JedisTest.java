import lee.detail.Student;
import lee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JedisTest extends TestBasic{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate") //神奇吗
    private ValueOperations<String,Student> valueOperations;
    @Test
    public void jedisTest() {
        List<JedisShardInfo> listinfo = new ArrayList<JedisShardInfo>();
        JedisShardInfo je1 = new JedisShardInfo("192.168.246.130",6379);
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
    @Test
    public void redisTemp(){
        Student student = new Student();
        student.setName("lkx");
        redisTemplate.opsForValue().set("lkx",student);
        Student s = (Student) redisTemplate.opsForValue().get("lkx");
        System.out.println(s.getName());
    }
    @Test
    public void valueOp() throws InterruptedException {
        Student student = new Student();
        student.setName("lkx");
        valueOperations.set("xxx",student);
        redisTemplate.expire("xxx",15,TimeUnit.SECONDS);
        System.out.println(".........................xxx15秒后失效");
        System.out.println(valueOperations.get("xxx"));
        Thread.sleep(15100);
        System.out.println(valueOperations.get("xxx"));
    }
}
