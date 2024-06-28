package com.iweb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.iweb.config.Result;
import com.iweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Author 杨芯叶
 * @Create 2024-06-25-13:58
 * @Message
 **/


@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println(request);
        // 1. 获取请求的URL
        // 2. 判断请求中是否有login

        // 3. 获取请求头中的令牌（token - jwt）
        String token =request.getHeader("token");

        // 输出请求头
        // Enumeration<String> headerName = request.getHeaderNames();
        // while (headerName.hasMoreElements()) {
        //     String nextElement = headerName.nextElement();
        //     String header = request.getHeader(nextElement);
        //     log.info("请求头："+ nextElement + "值：" + header);
        // }

        // 4. 判断令牌是否存在，不存在返回登录失败
        if (!StringUtils.hasLength(token)){
            log.info("token不存在");
            Result responseResult = Result.failed("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return false;
        }
        // 5. 解析token，解析失败返回登录失败
        try{
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("令牌解析失败");
            Result responseResult = Result.failed("NOT_LOGIN");
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return false;
        }
        // 6. 放行
        return true;
    }

}
