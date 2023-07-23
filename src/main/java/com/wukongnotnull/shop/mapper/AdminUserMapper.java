package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author wukong
* @description 针对表【shop_admin_user】的数据库操作Mapper
* @createDate 2023-07-11 15:37:22
* @Entity com.wukongnotnull.shop.domain.AdminUser
*/
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {


    AdminUser doLogin(@Param("userName") String userName,
                      @Param("password") String passwordMd5);


    Integer addAdminUser(@Param("adminUser") AdminUser adminUser);
}




