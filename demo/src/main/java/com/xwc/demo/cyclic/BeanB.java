package com.xwc.demo.cyclic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建人：徐卫超 CC
 * 时间：2020/12/13 14:06
 * 备注：
 */
@Component
public class BeanB {
	@Autowired
	public BeanA beanA;


}
