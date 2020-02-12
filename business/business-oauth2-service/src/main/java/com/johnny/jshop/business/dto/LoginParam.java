package com.johnny.jshop.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 * <p>
 * Description:
 * </p>
 *
 * @class LoginParam
 * @author JohnnyHao
 * @date 2020-02-12
 */
@Data
public class LoginParam implements Serializable {

    private String username;

    private String password;

}
