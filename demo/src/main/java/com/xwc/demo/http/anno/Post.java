package com.xwc.demo.http.anno;

import org.springframework.core.annotation.AliasFor;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/4
 * 描述：
 */
public @interface Post {
	@AliasFor("value")
	String path() default "";

	@AliasFor("path")
	String value() default "";
}
