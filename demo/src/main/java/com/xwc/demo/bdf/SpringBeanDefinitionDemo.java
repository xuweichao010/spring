package com.xwc.demo.bdf;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/23 21:36
 * 备注：
 */
@Configuration
@ImportResource("classpath:bd.xml")
public class SpringBeanDefinitionDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringBeanDefinitionDemo.class);
		XmlBean1 bean = app.getBean(XmlBean1.class);
		System.out.println(bean.getXmlBean2());
		System.out.println(((AbstractBeanDefinition) app.getBeanDefinition("xmlBean1")).getAutowireMode());
	}
}
