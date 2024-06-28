package com.iweb.config;

import com.iweb.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author 杨芯叶
 * @Create 2024-06-07-15:18
 * @Message
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")

                // 设置允许跨域请求的域名 单个源：http://localhost:8888
                .allowedOriginPatterns("*")

                // 是否允许cookie
                .allowCredentials(true)

                // 设置允许的请求方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")

                // 设置允许的header属性
                .allowedHeaders("*")

                // 跨域允许时间
                .maxAge(3600);
    }

    /*
    ** 拦截配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 一级路径
                // .addPathPatterns("/*")
                // 任意路径
                .addPathPatterns("/**")
                // 指定路径不拦截
                .excludePathPatterns("/login");
                // depts下的任意一个路径
                // .addPathPatterns("/depts/*")
                // depts下的任意一个路径
                // .addPathPatterns("/depts/**");
    }
}
