package com.xwc.demo.http;

import com.xwc.demo.http.anno.Get;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/4
 * 描述：
 */
public class PorxyDemo<T> {

	public static void main(String[] args) {
//		Class<T> baiduRpcClass = BaiduRpc.class;
//		Object o = Proxy.newProxyInstance(baiduRpcClass.getClassLoader(), new Class[]{baiduRpcClass}, new InvocationHandler() {
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				System.out.println(proxy);
//				return proxy;
//			}
//		});
//		System.out.println(o);
	}
}
