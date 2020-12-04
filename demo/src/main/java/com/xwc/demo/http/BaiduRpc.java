package com.xwc.demo.http;

import com.xwc.demo.http.anno.Get;
import com.xwc.demo.http.anno.HttpClient;

/**
 * 作者：徐卫超 cc
 * 时间：2020/12/4
 * 描述：百度接口调用
 */
@HttpClient(url = "www.baidu.com")
public interface BaiduRpc {
	@Get
	String get();
}
