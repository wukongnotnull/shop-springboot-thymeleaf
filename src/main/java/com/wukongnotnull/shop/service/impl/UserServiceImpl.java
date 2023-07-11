package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.User;
import com.wukongnotnull.shop.service.UserService;
import com.wukongnotnull.shop.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-07-11 14:24:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




