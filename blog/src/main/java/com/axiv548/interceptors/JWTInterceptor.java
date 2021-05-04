package com.axiv548.interceptors;

import com.axiv548.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * JWTInterceptor
 *
 * @date 2021/3/4 2:43
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        获取token
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
            return true;
        } catch (Exception e) {
            PrintWriter out = null;
            String res = "401";
            out = response.getWriter();
            out.append(res);
            return false;
        }
    }
}
