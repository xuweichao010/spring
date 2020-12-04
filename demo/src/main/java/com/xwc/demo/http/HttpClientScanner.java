package com.xwc.demo.http;

import com.xwc.demo.http.core.HttpClientRegistrar;
import com.xwc.demo.http.core.HttpScannerConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/3
 * 描述：
 */

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HttpClientRegistrar.class)
public @interface HttpClientScanner {
	String value() default "";

}
