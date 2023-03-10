package com.xiaomaotongzhi.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {
    private Integer id ;
    private String username ;
    private String phone ;
    private String password ;
    private Integer isDelete ;
    @TableField(exist = false)
    private List<String> permissions ;
}
