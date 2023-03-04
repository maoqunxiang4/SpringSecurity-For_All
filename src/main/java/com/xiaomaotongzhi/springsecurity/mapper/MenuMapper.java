package com.xiaomaotongzhi.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomaotongzhi.springsecurity.entity.Menu;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getAllMenus(Integer id) ;
}
