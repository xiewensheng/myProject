package com.wjw.graduationproject.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebAppConfig implements WebMvcConfigurer  {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器,addPathPatterns拦截方法，excludePathPatterns不拦截的方法
		registry.addInterceptor(new InterceptorConfig())
            .addPathPatterns("/**")
            .excludePathPatterns(Arrays.asList("/test/login","/error"));
	}

}
