package com.xwc.demo.http;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/23 21:19
 * 备注：
 */
@Configuration
@HttpClientScanner
public class HttpClientDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(HttpClientDemo.class);
		BaiduRpc bean = app.getBean(BaiduRpc.class);
		System.out.println(bean);

	}
}
