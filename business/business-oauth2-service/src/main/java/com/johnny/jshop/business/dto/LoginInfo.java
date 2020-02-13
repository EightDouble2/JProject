package com.johnny.jshop.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
 * <p>
 * Description: 
 * </p>
 *
 * @class LoginInfo
 * @author JohnnyHao
 * @date 2020-02-12 
 */ 
@Data
public class LoginInfo implements Serializable {

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 头像
     */
    private String nickName;


}
