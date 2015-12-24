package com.ane.framework.Base.network;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpConnUtils {
//  定义sessionId
//	暂时屏蔽cookie
//	public static String JSESSIONID;
//	public static String JSESSIONID2;
	/**
	 * 连接请求
	 * 
	 * @param argUrl
	 *            请求URL
	 * @param putParam
	 *            参数
	 * @return String 字符串结果
	 */
	public static ResultMsg connRequest(String argUrl,
			Map<String, Object> putParam,long connectionTimeout,long socketTimeout) {

		ResultMsg resultMsg = new ResultMsg();

		HttpPost request = new HttpPost(argUrl);
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		Set<String> key = putParam.keySet();
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			postParams
					.add(new BasicNameValuePair(s, putParam.get(s).toString()));
		}
//		暂时屏蔽cookie
//		if (null != JSESSIONID & (argUrl.indexOf(Constants.doMain) >= 0)) {
//			request.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);
//		}
//		if (null != JSESSIONID2 & (argUrl.indexOf(Constants.doMain1) >= 0)) {
//			request.setHeader("Cookie", "JSESSIONID=" + JSESSIONID2);
//		}
		try {
			request.setEntity(new UrlEncodedFormEntity(postParams, HTTP.UTF_8));

			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					socketTimeout);

			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
//				暂时屏蔽cookie
//				CookieStore cookieStore = ((AbstractHttpClient) client)
//						.getCookieStore();
//				List<Cookie> cookies = cookieStore.getCookies();
//				if (JSESSIONID == null
//						& (argUrl.indexOf(Constants.doMain) >= 0)) {
//					for (int i = 0; i < cookies.size(); i++) {
//						if ("JSESSIONID".equals(cookies.get(i).getName())) {
//							JSESSIONID = cookies.get(i).getValue();
//							break;
//						}
//					}
//				}
//				if (JSESSIONID2 == null
//						& (argUrl.indexOf(Constants.doMain1) >= 0)) {
//					for (int i = 0; i < cookies.size(); i++) {
//						if ("JSESSIONID".equals(cookies.get(i).getName())) {
//							JSESSIONID2 = cookies.get(i).getValue();
//							break;
//						}
//					}
//				}
				resultMsg.setResult(true);
				resultMsg.setResultInfo(result);
				resultMsg.setReason("获取成功");
			} else {
				resultMsg.setResult(false);
				resultMsg.setReason("服务请求失败");
			}
		} catch (Exception e) {
			resultMsg.setResult(false);
			resultMsg.setReason("服务器连接失败");
			e.printStackTrace();
		}
		return resultMsg;
	}
}
