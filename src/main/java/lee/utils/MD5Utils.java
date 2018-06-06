package lee.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 工具类  先不加盐
 * Created by Allen on 20/05/2017.
 * coder.allen@hotmail.com
 */
public class MD5Utils {
	private static Logger logger =  LoggerFactory.getLogger(MD5Utils.class);
	
	private static final String salt = "MOA";
	
	/**
	 * 加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encode(String str) throws Exception{
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * 验证
	 * @param str
	 * @param md5str
	 * @return
	 */
	public static boolean verify(String str,String md5str){
		//MD5验证
		if(DigestUtils.md5Hex(str).equals(md5str)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		StringBuffer strb = new StringBuffer();
		strb.append("9999999");//系统ID
		strb.append("test_465416745641540");//流水号
		strb.append(0.1);//金额
		strb.append("http://www.163.com/xx");//异步通知地址
		strb.append("95b4cc636b1b285584bd4e5c6668da23");//系统加密KEY
		System.out.println(strb.toString());
		System.out.println(DigestUtils.md5Hex(strb.toString()));
	}
	

}
