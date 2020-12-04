package com.xwc.demo.http.core;

import com.xwc.demo.http.anno.Get;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */

public class HttpClientFactoryBean<T> implements FactoryBean<T> {
	private Class<T> httpClient;
	private String url;

	@Override
	@SuppressWarnings("unchecked")
	public T getObject() throws Exception {
		return (T)getMapper(httpClient);
	}

	public Object getMapper(Class<T> clazz) {
		Class<?>[] classes = new Class<?>[]{clazz};
		Object o = Proxy.newProxyInstance(HttpClientFactoryBean.class.getClassLoader(), classes, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("conn db");
				return null;
			}
		});
		System.out.println(o);
		return  o;
	}



	public HttpClientFactoryBean(Class<T> httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Class<T> getObjectType() {
		return this.httpClient;
	}

	public void setHttpClient(Class<T> httpClient) {
		this.httpClient = httpClient;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpClientFactoryBean() {
	}
}
