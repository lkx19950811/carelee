package lee.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * Http通讯类
 *
 * @author Leo
 * @create 2018-01-10 下午 4:17
 */
public class HttpClient {
    private static Logger logger = Logger.getLogger(HttpClient.class);

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject get(String url) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * post请求
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static JSONObject post(String url,Object param){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        //拼接参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Map<String, String> params = JSONObject.parseObject(JSONObject.toJSONString(param), new TypeReference<Map<String, String>>(){});
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.out.println("key=" + key + " value=" + value);
            NameValuePair pair = new BasicNameValuePair(key, value);
            list.add(pair);
        }
        CloseableHttpResponse response=null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpclient.execute(httpPost);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**请求发送成功，并得到响应**/
        JSONObject jsonObject=null;
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            HttpEntity httpEntity = response.getEntity();
            String result=null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }// 返回json格式：
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

}
