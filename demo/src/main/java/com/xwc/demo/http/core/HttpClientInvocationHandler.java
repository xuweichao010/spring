package com.xwc.demo.http.core;

import com.alibaba.fastjson.JSONObject;
import com.xwc.demo.http.anno.Get;
import com.xwc.demo.http.anno.Post;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/4
 * 描述：
 */
public class HttpClientInvocationHandler implements InvocationHandler {
	private static Set<Class<? extends Annotation>> HTTP_ANNOTATION_TYPES = new HashSet<>();

	static {
		HTTP_ANNOTATION_TYPES.add(Get.class);
		HTTP_ANNOTATION_TYPES.add(Post.class);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Annotation httpAnnotationType = getHttpAnnotationType(method);
		String responseMessage;
		if (httpAnnotationType instanceof Get) {
			responseMessage = "{}";
		} else if (httpAnnotationType instanceof Post) {
			responseMessage = "{}";
		} else {
			throw new UnsupportedOperationException("不支持该类型的HTTP请求");
		}
		Class<?> returnType = method.getReturnType();
		if (returnType.isAssignableFrom(Void.class)) {
			return null;
		} else if (returnType.isAssignableFrom(Collection.class)) {
			//获取泛型信息
			return JSONObject.parseArray(responseMessage, Object.class);
		} else {
			return JSONObject.parseObject(responseMessage, returnType);
		}

	}

	String httpGet(String url, Method method) {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Get请求
		Get annotation = AnnotationUtils.findAnnotation(method, Get.class);
		HttpGet httpGet = new HttpGet(url + annotation.path());
		Parameter[] parameters = method.getParameters();
		StringBuilder sb = new StringBuilder();
		for (Parameter parameter : parameters) {

		}
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Get请求
			response = httpClient.execute(httpGet);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				return EntityUtils.toString(responseEntity);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	private Annotation getHttpAnnotationType(Method method) {
		return chooseAnnotationType(method, HTTP_ANNOTATION_TYPES);
	}

	private Annotation chooseAnnotationType(Method method, Set<Class<? extends Annotation>> types) {
		for (Class<? extends Annotation> type : types) {
			Annotation annotation = AnnotationUtils.findAnnotation(method, type);
			if (annotation != null) {
				return annotation;
			}
		}
		return null;
	}

}
