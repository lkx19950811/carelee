package lee.utils;

import com.moamarkets.entity.Merchant;
import com.moamarkets.entity.Payment;
import com.moamarkets.entity.Withdrawal;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;

public class NotificationUtils {
	static Logger logger =  LoggerFactory.getLogger(NotificationUtils.class);
	
	/**
	 * 通知业务系统
	 * @param paymentRecord
	 * @return
	 */
	public static String notify(Payment paymentRecord,Merchant merchant) {
		logger.debug("Notify the target system payment status : ");
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String returnCode = "";
		try{
			httpclient = HttpClients.custom().build();
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).build();
			RequestBuilder builder = RequestBuilder.post().setConfig(requestConfig).setUri(new URI(paymentRecord.getNotificationURL()));
			
			builder.addParameter("merchantId",paymentRecord.getMerchantId());
			builder.addParameter("transId",paymentRecord.getTransId());
			builder.addParameter("amount",String.valueOf(paymentRecord.getAmount()));
			builder.addParameter("status",paymentRecord.getStatus());
			builder.addParameter("gateway",paymentRecord.getGateway());
			builder.addParameter("date",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(paymentRecord.getDate()));
			if(paymentRecord.getExtraParam() != null && !paymentRecord.getExtraParam().equals("")){
				builder.addParameter("extraParam",paymentRecord.getExtraParam());
			}
			builder.addParameter("sign",MD5Utils.encode(paymentRecord.getMerchantId() + paymentRecord.getTransId() + paymentRecord.getAmount() + paymentRecord.getStatus() + merchant.getMerchantKey()));
			
			response = httpclient.execute(builder.build());
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				String responseText = EntityUtils.toString(entity,"UTF-8");
				logger.debug("Target system return : " + responseText + ",transId : " + paymentRecord.getTransId() + ",address : " + paymentRecord.getNotificationURL());
				if(responseText.trim().equals(Constants.NOTIFICATION_STATUS_DONE)){
					returnCode = Constants.NOTIFICATION_STATUS_DONE;
				}else{
					returnCode = Constants.NOTIFICATION_STATUS_FAILD;
				}
			}else{
				logger.debug("Target system can not be connected : " + response.getStatusLine() + ",transId : " + paymentRecord.getTransId() + ",address : " + paymentRecord.getNotificationURL());
				returnCode = Constants.NOTIFICATION_STATUS_FAILD;
			}
		}catch(Exception e){
			logger.debug("Target system can not be connected : " + e.getMessage() + ",transId : " + paymentRecord.getTransId() + ",address : " + paymentRecord.getNotificationURL());
			returnCode = Constants.NOTIFICATION_STATUS_FAILD;
		}finally{
			try {
				if(httpclient != null){
					httpclient.close();
				}
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		return returnCode;
	}
	
	
	/**
	 * 通知业务系统
	 * @param withdrawalRecord
	 * @return
	 */
	public static String notify(Withdrawal withdrawalRecord,Merchant merchant) {
		logger.debug("Notify the target system withdrawal status : ");
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String returnCode = "";
		try{
			httpclient = HttpClients.custom().build();
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).build();
			RequestBuilder builder = RequestBuilder.post().setConfig(requestConfig).setUri(new URI(withdrawalRecord.getNotificationURL()));
			
			builder.addParameter("merchantId",withdrawalRecord.getMerchantId());
			builder.addParameter("transId",withdrawalRecord.getTransId());
			builder.addParameter("amount",String.valueOf(withdrawalRecord.getAmount()));
			builder.addParameter("status",withdrawalRecord.getStatus());
			builder.addParameter("date",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(withdrawalRecord.getDate()));
			if(StringUtils.isNotBlank(withdrawalRecord.getExtraParam())){
				builder.addParameter("extraParam",withdrawalRecord.getExtraParam());
			}
			if(StringUtils.isNotBlank(withdrawalRecord.getComment())){
				builder.addParameter("comment",withdrawalRecord.getComment());
			}
			builder.addParameter("sign",MD5Utils.encode(withdrawalRecord.getMerchantId() + withdrawalRecord.getTransId() + withdrawalRecord.getAmount() + withdrawalRecord.getStatus() + merchant.getMerchantKey()));
			
			response = httpclient.execute(builder.build());
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				String responseText = EntityUtils.toString(entity,"UTF-8");
				logger.debug("Target system return : " + responseText + ",transId : " + withdrawalRecord.getTransId() + ",address : " + withdrawalRecord.getNotificationURL());
				if(responseText.trim().equals(Constants.NOTIFICATION_STATUS_DONE)){
					returnCode = Constants.NOTIFICATION_STATUS_DONE;
				}else{
					returnCode = Constants.NOTIFICATION_STATUS_FAILD;
				}
			}else{
				logger.debug("Target system can not be connected : " + response.getStatusLine() + ",transId : " + withdrawalRecord.getTransId() + ",address : " + withdrawalRecord.getNotificationURL());
				returnCode = Constants.NOTIFICATION_STATUS_FAILD;
			}
		}catch(Exception e){
			logger.debug("Target system can not be connected : " + e.getMessage() + ",transId : " + withdrawalRecord.getTransId() + ",address : " + withdrawalRecord.getNotificationURL());
			returnCode = Constants.NOTIFICATION_STATUS_FAILD;
		}finally{
			try {
				if(httpclient != null){
					httpclient.close();
				}
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		return returnCode;
	}
	
}
