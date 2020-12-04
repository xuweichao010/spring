package com.xwc.demo.http.core;

import com.xwc.demo.http.anno.HttpClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * 作者：CC
 * 时间：2020/8/31 11:11
 * 描述：Feign注解扫描器
 */


public class ClassPathHttpClientScanner extends ClassPathBeanDefinitionScanner {

	private String basePackage;


	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface()
				&& beanDefinition.getMetadata().hasAnnotation(HttpClient.class.getName());
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		registerFilters();
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		if (beanDefinitions.isEmpty()) {
			logger.warn("No Feign was found in" + Arrays.toString(basePackages) + " package. Please check your configuration.");
		} else {
			processBeanDefinitions(beanDefinitions);
		}
		return beanDefinitions;
	}


	private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			String url = feignUrl(holder);
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(definition.getBeanClassName()));
			definition.setBeanClass(HttpClientFactoryBean.class);
			definition.getPropertyValues().add("url", url);
			definition.setLazyInit(false);
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
			assert this.getRegistry() != null;
			this.getRegistry().registerBeanDefinition(holder.getBeanName(), definition);
		}
	}

	private String feignUrl(BeanDefinitionHolder beanDefinition) {
		HttpClient httpClient = httpClient(beanDefinition);
		String propertyUrl = getEnvironment().getProperty(httpClient.url());
		if (StringUtils.hasText(propertyUrl)) {
			return propertyUrl;
		}
		return httpClient.url();
	}

	public void registerFilters() {
		this.resetFilters(false);
		this.addIncludeFilter(new AnnotationTypeFilter(HttpClient.class));

	}

	private HttpClient httpClient(BeanDefinitionHolder beanDefinition) {
		HttpClient annotation = null;
		try {
			annotation = AnnotationUtils.findAnnotation(Class.forName(beanDefinition.getBeanDefinition().getBeanClassName()), HttpClient.class);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(beanDefinition.getBeanDefinition().getBeanClassName(), e);
		}
		if (annotation == null) throw new RuntimeException("找不到注解信息" + HttpClient.class.getName());
		return annotation;
	}

	public ClassPathHttpClientScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}


	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}


}
