package com.xwc.demo.http.core;

import com.xwc.demo.http.anno.HttpClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */
public class HttpScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

	private String basePackage;

	private Class<HttpClientFactoryBean<?>> factoryBean;

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		ClassPathHttpClientScanner scanner = new ClassPathHttpClientScanner(registry);
		scanner.setBasePackage(basePackage);
		scanner.scan(basePackage);

	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}


	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public Class<HttpClientFactoryBean<?>> getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(Class<HttpClientFactoryBean<?>> factoryBean) {
		this.factoryBean = factoryBean;
	}
}
