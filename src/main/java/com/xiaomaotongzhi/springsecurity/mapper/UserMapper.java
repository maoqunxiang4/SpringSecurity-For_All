package com.xiaomaotongzhi.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomaotongzhi.springsecurity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}