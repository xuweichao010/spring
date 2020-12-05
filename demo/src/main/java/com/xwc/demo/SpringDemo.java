package com.xwc.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/23 21:19
 * 备注：
 */
@Configuration
@ComponentScan
public class SpringDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringDemo.class);
		SpringDemo bean = app.getBean(SpringDemo.class);
		System.out.println(bean);

	}
}
