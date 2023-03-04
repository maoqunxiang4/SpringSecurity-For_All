package com.xiaomaotongzhi.springsecurity.controller;

import com.xiaomaotongzhi.springsecurity.service.IUserService;
import com.xiaomaotongzhi.springsecurity.service.Impl.UserServiceImpl;
import com.xiaomaotongzhi.springsecurity.utils.Result;
import io.netty.handler.codec.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService ;

    @PostMapping("/login")
    public Result Login(String username , String password){
        Result result = userService.Login(username, password);
        return result;
    }

    @DeleteMapping("/logout")
    public Result Logout(HttpServletRequest request){
        return userService.Logout(request);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('testq')")
    public String hello(){
        return "hello" ;
    }
}
