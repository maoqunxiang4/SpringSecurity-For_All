package com.xiaomaotongzhi.springsecurity.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.xiaomaotongzhi.springsecurity.entity.LoginUser;
import com.xiaomaotongzhi.springsecurity.entity.User;
import com.xiaomaotongzhi.springsecurity.service.IUserService;
import com.xiaomaotongzhi.springsecurity.utils.Result;
import io.netty.handler.codec.http.HttpRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate ;

    public Result Login(String username, String password) {
        //封装Authortion对象
        UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(AuthenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("there is a wrong now") ;
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String token = UUID.randomUUID().toString();
        User us = loginUser.getUser();
        Map<String, Object> map = BeanUtil.beanToMap(
                us, new HashMap<>() ,
                CopyOptions.create().setIgnoreNullValue(true).setFieldValueEditor((filedName,filedValue)->{
                    if(filedValue == null){
                        filedValue = "0" ;
                    }else {
                        filedValue = filedValue.toString() ;
                    }
                    return  filedValue;
                }));
        map.put("permissions",loginUser.getPermissions().toString()) ;
        stringRedisTemplate.opsForHash().putAll(token , map);
        stringRedisTemplate.expire(token , 30 ,TimeUnit.MINUTES) ;
        return Result.success(200,token);
    }

    @Override
    public Result Logout(HttpServletRequest request) {
        //可以通过SecurityContextHolder获取Authentication和其内部存储的对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //loginUser
        String token = request.getHeader("token");
        stringRedisTemplate.delete(token) ;
        return Result.success(200);
    }
}
