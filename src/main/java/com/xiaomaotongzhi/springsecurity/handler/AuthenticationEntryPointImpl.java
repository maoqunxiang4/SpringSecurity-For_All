package com.xiaomaotongzhi.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.xiaomaotongzhi.springsecurity.utils.ResponseUtils;
import com.xiaomaotongzhi.springsecurity.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "用户未完成认证");
        String jsonString = JSON.toJSONString(result);
        ResponseUtils.renderString(response,jsonString) ;
    }
}
