import org.junit.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-03-13 下午 11:23
 */
public class JedisTest {

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
}
