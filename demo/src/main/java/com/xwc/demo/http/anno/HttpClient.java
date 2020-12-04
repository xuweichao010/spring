package com.xwc.demo.http.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：生命对象处理HTTP请求
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpClient {

	@AliasFor("value")
	String url() default "";

	@AliasFor("url")
	String value() default "";
}
