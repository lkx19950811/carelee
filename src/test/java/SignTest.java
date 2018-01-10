import lee.utils.Constants;
import lee.utils.SginMethod;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-01-11 上午 1:28
 */
public class SignTest {
    @Test
    public void signTest() throws IOException, NoSuchAlgorithmException {
        Map<String,String> map = new TreeMap<String,String>();
        map.put("method","taobao.item.seller.get");
        map.put("app_key","12345678");
        map.put("session","test");
        map.put("timestamp","2016-01-01 12:00:00");
        map.put("format","json");
        map.put("v","2.0");
        map.put("sign_method","md5");
        map.put("fields","num_iid,title,nick,price,num");
        map.put("num_iid","11223344");
        String sgin = SginMethod.signTopRequest(map,"helloworld", Constants.SIGN_METHOD_MD5);
        System.out.println(sgin);
    }
}
