package com.xwc.demo.bdf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/11/29 14:41
 * 备注：
 */
@Configuration
public class ConfigurationBean {

	@Bean()
	public AnnotationBean annotationBean() {
		return new AnnotationBean();
	}
}
