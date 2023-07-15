package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.wukongnotnull.shop.service.OrdinaryUserService;
import com.wukongnotnull.shop.mapper.OrdinaryUserMapper;
import com.wukongnotnull.shop.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 悟空非空也
* @description 针对表【shop_ordinary_user】的数据库操作Service实现
*/
@Service
public class OrdinaryUserServiceImpl extends ServiceImpl<OrdinaryUserMapper, OrdinaryUser>
    implements OrdinaryUserService{

    @Autowired
    private OrdinaryUserMapper ordinaryUserMapper;

    /**
     * 根据用户名和密码添加用户（注册）
     *
     * @param registerName  注册名
     * @param password   注册密码
     * @return  注册结果
     */
    @Override
    public String addOrdinaryUser(String registerName, String password) {
        // 注册名不能重复
       if(ordinaryUserMapper.selectOrdinaryUser(registerName) != null){
           return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
       }

        OrdinaryUser registerUser = new OrdinaryUser();
       // 设置登录名
        registerUser.setLoginName(registerName);
        // 密码需要 MD5 加密
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMd5);
        // 设置默认昵称同注册名（登录名）
        registerUser.setNickName(registerName);

        if(ordinaryUserMapper.insertOrdinaryUser(registerUser) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }else {
            return  ServiceResultEnum.DB_ERROR.getResult();
        }
    }
}




