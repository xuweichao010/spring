package com.xwc.demo.bdf;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/23 21:36
 * 备注：
 */
@Configuration
@ImportResource("classpath:bd.xml")
@ComponentScan("com.xwc.demo.bdf")
public class SpringBeanDefinitionDemo {
	public static void main(String[] args) {
		//配置类

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringBeanDefinitionDemo.class);
		System.out.println("--------------------  xml配置  ----------------------------");
		System.out.println("xmlParent 的BeanDefinition类型：" + app.getBeanDefinition("xmlParent").getClass().getSimpleName());
		System.out.println("xmlBean1 的BeanDefinition类型：" + app.getBeanDefinition("xmlBean1").getClass().getSimpleName());

		//注解
		System.out.println("--------------------  注解配置  ----------------------------");
		System.out.println("configurationBean 的BeanDefinition类型：" + app.getBeanDefinition("configurationBean").getClass().getSimpleName());
		System.out.println("annotationBean 的BeanDefinition类型：" + app.getBeanDefinition("annotationBean").getClass().getSimpleName());
		System.out.println("componentBean1 的BeanDefinition类型：" + app.getBeanDefinition("componentBean1").getClass().getSimpleName());
		System.out.println("springBeanDefinitionDemo 的BeanDefinition类型：" + app.getBeanDefinition("springBeanDefinitionDemo").getClass().getSimpleName());
		//装配测试
		System.out.println("AutowireCandidateBean 设置了autowire-candidate=false :" + app.getBean(XmlBean2.class).getAutowireCandidateBean());
	}
}
