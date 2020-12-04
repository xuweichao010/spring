package com.xwc.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */
@Component
public class BeanDefinitionRegistryPostProcessorDemo implements BeanDefinitionRegistryPostProcessor, InitializingBean {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		BeanDefinition beanDemo = registry.getBeanDefinition("beanDemo");
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition("com.xwc.demo.bean.RegistryBean").setLazyInit(false).getBeanDefinition();
		registry.registerBeanDefinition("registryBean", beanDefinition);
		System.out.println("【BeanDefinitionRegistryPostProcessor - postProcessBeanDefinitionRegistry()】 " + beanDemo);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDemo = beanFactory.getBeanDefinition("beanDemo");
		System.out.println("【BeanDefinitionRegistryPostProcessor - postProcessBeanFactory()】 " + beanDemo);
	}

	@Autowired
	private SpringBeanLifeDemo springBeanLifeDemo;


	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【BeanDefinitionRegistryPostProcessorDemo - InitializingBean】 " + springBeanLifeDemo);
	}
}
