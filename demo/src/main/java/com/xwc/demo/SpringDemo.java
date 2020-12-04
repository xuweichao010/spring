package com.xwc.demo;

import com.xwc.demo.http.BaiduRpc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/23 21:19
 * 备注：
 */
@Configuration
public class SpringDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringDemo.class);
		Object bean = app.getBean("baiduRpc");
		System.out.println(bean);


	}
}
