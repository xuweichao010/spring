package com.xwc.demo.cyclic;

import com.xwc.demo.SpringDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/12/13 14:05
 * 备注：
 */
@ComponentScan
public class CyclicReferencesDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(CyclicReferencesDemo.class);
		BeanA bean = app.getBean(BeanA.class);
		System.out.println(bean.beanB);

	}
}
