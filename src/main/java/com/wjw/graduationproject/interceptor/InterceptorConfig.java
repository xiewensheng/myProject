package com.wjw.graduationproject.interceptor;

import com.wjw.graduationproject.entity.Audience;
import com.wjw.graduationproject.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptorConfig implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);
	
	/** 
     * 进入controller层之前拦截请求 
     * @param request
     * @param response
     * @param handler
     * @return
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		
		log.info("----开始请求拦截----");
		String uri = request.getRequestURI();
		log.info("请求uri:"+uri);
		log.info("JWT拦截开始");

		//JWT拦截开始
		final String authHeader = request.getHeader("authorization");//获取请求头的jwt的cookie
		if ("OPTIONS".equals(request.getMethod())){//options是一个是一个http方法
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}else{
			if (authHeader == null || authHeader.startsWith("bearer")){//判断请求头是否包含jwt的cookie
				log.info("JWT验证失败,请求头信息错误");
				return false;
			}
		}
		final String token = authHeader.substring(7);//获取cookie中的信息
		try {
			final Claims claims = JwtHelper.parseJWT(token,Audience.getBase64Secret());//解析jwt的cookie
			if (claims == null){
				log.info("解析JWT失败");
				return false;
			}
			request.setAttribute("CLAIMS",claims);
			log.info("JWT拦截结束");
			return true;
		}catch (Exception e){
			throw e;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
