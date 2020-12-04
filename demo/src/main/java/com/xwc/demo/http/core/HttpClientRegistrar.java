package com.xwc.demo.http.core;

import com.xwc.demo.http.HttpClientScanner;
import com.xwc.demo.http.anno.HttpClient;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：注入HTTPClient 核心配置对象
 */
public class HttpClientRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		AnnotationAttributes mapperScanAttrs = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(HttpClientScanner.class.getName()));
		if (mapperScanAttrs != null) {
			registerBeanDefinitions(importingClassMetadata, mapperScanAttrs, registry,
					generateBaseBeanName(importingClassMetadata));
			System.out.println(generateBaseBeanName(importingClassMetadata));
		}
	}

	void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs,
								 BeanDefinitionRegistry registry, String beanName) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(HttpScannerConfigurer.class);
		//包扫描信息
		List<String> basePackages = new ArrayList<>();
		basePackages.addAll(
				Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
		if (basePackages.isEmpty()) {
			basePackages.add(getDefaultBasePackage(annoMeta));
		}
		builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackages));
		//factoryBean
		builder.addPropertyValue("factoryBean", HttpClientFactoryBean.class);
		//注册bean的定义信息
		registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
	}

	private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata) {
		return importingClassMetadata.getClassName() + "#" + HttpClientRegistrar.class.getSimpleName();
	}

	public static String generateBaseBeanName(Class<?> classInfo) {
		return classInfo.getSimpleName() + "#" + HttpClientRegistrar.class.getSimpleName();
	}

	private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
		return ClassUtils.getPackageName(importingClassMetadata.getClassName());
	}

}
