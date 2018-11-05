package com.wjw.graduationproject.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebAppConfig implements WebMvcConfigurer  {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new InterceptorConfig())
		.excludePathPatterns("/app/**").excludePathPatterns("/login.html").addPathPatterns("/**");
	}

}
