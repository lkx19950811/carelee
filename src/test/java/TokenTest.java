import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;

public class TokenTest {
    /**
     * JWT token 生成
     */
    @Test
    public void createToken(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username","李凯鑫")
                    .withClaim("password","lkx")
                    .sign(algorithm);
            System.out.println(token);
            decodeToken(token);
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    /**
     * token 解码 获取信息
     * @param token
     * @throws UnsupportedEncodingException
     */
    public void decodeToken(String token) throws UnsupportedEncodingException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("secret"))
                                    .withIssuer("auth0")
                                    .build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Map<String,Claim> claims = jwt.getClaims();
        System.out.println(claims);
        System.out.println(claims.get("username"));
    }

    /**
     * 手动md5加密
     */
    @Test
    public void Md5() {
        String key = "lkx";//需要加密的字符串
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };//也可以是小写字母char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' }
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 无符号右移,取高四位
                str[k++] = hexDigits[byte0 & 0xf]; // 取低四位
            }
            System.out.println(new String(str));
        } catch (Exception e) {
        }
    }

    /**
     * 使用DigestUtils 处理md5加密
     */
    @Test
    public void Md5Salt(){
        //使用org.apache.commons.codec.digest.DigestUtils 直接进行md5加密,是上面方法的封装
        String md5 = DigestUtils.md5Hex("yourpassword" + "salt");
        System.out.println(md5);
    }
}
