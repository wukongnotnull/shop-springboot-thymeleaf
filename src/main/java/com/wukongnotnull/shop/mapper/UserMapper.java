package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wukong
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-07-11 14:24:08
* @Entity com.wukongnotnull.shop.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




