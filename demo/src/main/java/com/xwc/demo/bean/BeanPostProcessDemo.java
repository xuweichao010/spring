package com.xwc.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */
@Component
public class BeanPostProcessDemo implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (BeanDemo.isShow(beanName)) {
			System.out.println("【BeanPostProcessor - postProcessBeforeInitialization】 " + beanName);
		} else {
			System.out.println("【BeanPostProcessor - postProcessBeforeInitialization】 " + beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (BeanDemo.isShow(beanName)) {
			System.out.println("【BeanPostProcessor - postProcessAfterInitialization】 " + beanName);
		} else {
			System.out.println("【BeanPostProcessor - postProcessAfterInitialization】 " + beanName);
		}
		return bean;
	}


}
