package com.xwc.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：ce
 */
@Component
public class BeanFactoryPostProcessorDemo implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDemo = beanFactory.getBeanDefinition("beanDemo");
		System.out.println("【BeanFactoryPostProcessorDemo#postProcessBeanFactory()】" + beanDemo.getBeanClassName());

	}
}
