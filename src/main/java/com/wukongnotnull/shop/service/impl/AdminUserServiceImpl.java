package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.domain.AdminUser;
import com.wukongnotnull.shop.service.AdminUserService;
import com.wukongnotnull.shop.mapper.AdminUserMapper;
import org.springframework.stereotype.Service;

/**
* @author wukong
* @description 针对表【shop_admin_user】的数据库操作Service实现
* @createDate 2023-07-11 15:37:22
*/
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>
    implements AdminUserService{

}




