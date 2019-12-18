package 火车票抢购;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Post {

	public static void main(String[] args) {
		String result = null;
		// 请求地址
		String url = "http://m.aikansj.cn/api.php/provide/art/?ac=list";
		//以集合的 传递值
		List<NameValuePair> parameForToken = new ArrayList<NameValuePair>();
		//data参数
//		parameForToken.add(new BasicNameValuePair("appKey", "a5746267b1fa4d0489b643706236a8ac"));
//		parameForToken.add(new BasicNameValuePair("appSecret", "aae7b3a877341a47bd5258dfd9bd2f18"));
		// 获取httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			// 创建post请求
			HttpPost httpPost = new HttpPost(url);
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
			httpPost.setConfig(requestConfig);
	
			// 提交参数发送请求
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameForToken);
			httpPost.setEntity(urlEncodedFormEntity);
			response = httpclient.execute(httpPost);
			// 得到响应信息
			int statusCode = response.getStatusLine().getStatusCode();
			// 判断响应信息是否正确
			if (statusCode != HttpStatus.SC_OK) {
				// 终止并抛出异常
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// result = EntityUtils.toString(entity);//不进行编码设置
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			// 关闭所有资源连接
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(result);
	}
}
