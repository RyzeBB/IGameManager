package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.alibaba.fastjson.JSON;
import com.igame.app.vo.RequestVO;
import com.igame.app.vo.ResponseVO;

public class HttpClientTest {

	public static void main(String[] args) throws HttpException, IOException {

		RequestVO requestVO = new RequestVO();
		requestVO.setDeviceId("999988889");
		requestVO.setActionCode(10001);
		requestVO.setAppid(1L);
		requestVO.setAppVersionCode("1.0.1");
		requestVO.setChannel("腾讯平台");
		requestVO.setSystemName("IOS");
		requestVO.setDeviceModel("IPHONE6");
		requestVO.setSystemVersion("IOS6");

		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod("http://**:8080/IGameManager/shop/hot");
		 List<NameValuePair> params=new ArrayList<NameValuePair>();  
         //建立一个NameValuePair数组，用于存储欲传送的参数  
         RequestEntity requestEntity = new StringRequestEntity(JSON.toJSONString(requestVO),"application/json","utf-8");
         method.setRequestEntity(requestEntity);

		int statusCode = client.executeMethod(method);

		if (statusCode != HttpStatus.SC_OK) {
			return;
		}
		String json = method.getResponseBodyAsString();
		System.out.println(json);
		ResponseVO responseVO = JSON.parseObject(json,ResponseVO.class);
		System.out.println(responseVO.getActionCode());
	}
}
