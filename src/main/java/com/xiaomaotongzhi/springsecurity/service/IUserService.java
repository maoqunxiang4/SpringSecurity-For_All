package com.xiaomaotongzhi.springsecurity.service;

import com.xiaomaotongzhi.springsecurity.utils.Result;
import io.netty.handler.codec.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    public Result Login(String username, String password) ;

    Result Logout(HttpServletRequest request);

}
