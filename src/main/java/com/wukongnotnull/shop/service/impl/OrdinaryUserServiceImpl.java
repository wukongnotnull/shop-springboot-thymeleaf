package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.ServiceResultEnum;
import com.wukongnotnull.shop.controller.vo.OrdinaryUserVO;
import com.wukongnotnull.shop.domain.OrdinaryUser;
import com.wukongnotnull.shop.service.CartItemService;
import com.wukongnotnull.shop.service.OrdinaryUserService;
import com.wukongnotnull.shop.mapper.OrdinaryUserMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import com.wukongnotnull.shop.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
* @author 悟空非空也
* @description 针对表【shop_ordinary_user】的数据库操作Service实现
*/
@Service
public class OrdinaryUserServiceImpl extends ServiceImpl<OrdinaryUserMapper, OrdinaryUser>
    implements OrdinaryUserService{

    @Autowired
    private  CartItemService cartItemService;

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

    /**
     * 进行登录操作
     *
     * @param loginName 登录账号名
     * @param password  登录密码
     * @return String
     */
    @Override
    public String handleLogin(String loginName,
                                    String password,
                                    HttpSession httpSession
    ) {
        // 根据 loginName 对数据库进行查询是否存在该用户
        OrdinaryUser ordinaryUser = ordinaryUserMapper.selectOrdinaryUser(loginName);
        if (ordinaryUser == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }

        // 对密码进行 md5 加密后，判断是否合法
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        if(!ordinaryUser.getPasswordMd5().equals(passwordMd5)){
            return ServiceResultEnum.PASSWORD_IS_ILLEGAL.getResult();
        }

        // OrdinaryUser to OrdinaryUserVO
        OrdinaryUserVO ordinaryUserVO = new OrdinaryUserVO();
        BeanUtil.copyProperties(ordinaryUser, ordinaryUserVO);


        //  根据 用户id 查询购物车中明细记录数
        Integer cartItemCount1 = cartItemService.getCartItemCount(ordinaryUser.getUserId());
        ordinaryUserVO.setCartItemCount(cartItemCount1);

        // 将 ordinaryUser 加入到 session 中
        httpSession.setAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY,ordinaryUserVO);

        return ServiceResultEnum.SUCCESS.getResult();
    }

    /**
     * 更新用户信息中的地址
     * @param ordinaryUser ordinaryUser
     * @param  httpSession httpSession
     * @return String
     */
    @Override
    public String updateAddress(OrdinaryUser ordinaryUser,HttpSession httpSession) {
        // 修改 修改人和修改时间字段
        ordinaryUser.setUpdateBy(ordinaryUser.getUserId());
        ordinaryUser.setUpdateTime(new Date());

       Integer i =  ordinaryUserMapper.updateOrdinaryUser(ordinaryUser);

       if(i == 1){
           // 更新的地址，存入到 Session 中
           OrdinaryUser ordinaryUser1 = ordinaryUserMapper.selectOrdinaryUserById(ordinaryUser.getUserId());
           OrdinaryUserVO tmpOrdinaryUserVO = new OrdinaryUserVO();
           BeanUtil.copyProperties(ordinaryUser1,tmpOrdinaryUserVO);
           httpSession.setAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY,tmpOrdinaryUserVO);
           return ServiceResultEnum.SUCCESS.getResult();
       }else{
           return ServiceResultEnum.ERROR.getResult();
       }
    }

    /**
     * by userId, address,introduceSign,nickName in ordinaryUserVO ,
     * update record data in shop_ordinary_user table
     * @param httpSession httpSession
     * @param ordinaryUserVO ordinaryUserVO
     * @return String
     */
    @Override
    public String modifyOrdinaryUserInfo(OrdinaryUserVO ordinaryUserVO,
                                         HttpSession httpSession) {

        OrdinaryUser ordinaryUser = new OrdinaryUser();
        BeanUtil.copyProperties(ordinaryUserVO,ordinaryUser);
        Integer i = ordinaryUserMapper.updateOrdinaryUser(ordinaryUser);
        if (i == 1) {
            // modify personal data in session
            OrdinaryUserVO ordinaryUserVOSession = (OrdinaryUserVO) httpSession.getAttribute(Constants.LOGIN_SUCCESS_SESSION_KEY);
            ordinaryUserVOSession.setAddress(ordinaryUserVO.getAddress());
            ordinaryUserVOSession.setIntroduceSign(ordinaryUserVO.getIntroduceSign());
            ordinaryUserVOSession.setNickName(ordinaryUserVO.getNickName());
            return ServiceResultEnum.SUCCESS.getResult();

        }

        return ServiceResultEnum.ERROR.getResult();
    }

}




