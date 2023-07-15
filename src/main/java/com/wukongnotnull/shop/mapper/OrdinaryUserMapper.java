package com.wukongnotnull.shop.mapper;

import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 悟空非空也
* @description 针对表【shop_ordinary_user】的数据库操作Mapper
*/
@Mapper
public interface OrdinaryUserMapper extends BaseMapper<OrdinaryUser> {

    /**
     * 根据注册用户名，查询该用户名是否存在
     * @param loginName  注册用户名
     * @return OrdinaryUser
     */
    OrdinaryUser selectOrdinaryUser(@Param("loginName")String loginName);


    /**
     *  添加用户功能
     * @param registerUser  注册用户对象
     * @return 是否添加成功
     */
    int insertOrdinaryUser(OrdinaryUser registerUser);
}




