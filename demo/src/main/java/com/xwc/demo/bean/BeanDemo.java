package com.xwc.demo.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：测试
 */
@Component
public class BeanDemo implements InitializingBean {
	public static final boolean show = false;
	@Autowired
	private SpringBeanLifeDemo springBeanLifeDemo;


	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("【InitializingBean】 " + springBeanLifeDemo);
	}

	public static boolean isShow(String beanName) {
		return beanName.equals("beanDemo");
	}
}
