package com.axiv548.config;

import com.axiv548.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * InterceptorConfig
 *
 * @date 2021/3/4 2:50
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        跨域拦截器
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
//        校验token拦截器
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/*/admin/**");
    }



}
