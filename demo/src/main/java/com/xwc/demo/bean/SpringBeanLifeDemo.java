package com.xwc.demo.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */

@ComponentScan("com.xwc.demo.bean")
public class SpringBeanLifeDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringBeanLifeDemo.class);
		RegistryBean bean = app.getBean(RegistryBean.class);
		System.out.println("注册Bean" + bean);
	}
}
