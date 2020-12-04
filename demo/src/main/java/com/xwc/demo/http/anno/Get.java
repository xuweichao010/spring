package com.xwc.demo.http.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：HTTP Get请求
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Get {
	@AliasFor("value")
	String path() default "";

	@AliasFor("path")
	String value() default "";
}
