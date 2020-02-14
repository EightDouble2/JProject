package com.johnny.jshop.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码参数
 * <p>
 * Description: 
 * </p>
 *
 * @class PasswordParam
 * @author JohnnyHao
 * @date 2020-02-14 
 */ 
@Data
public class PasswordParam implements Serializable {

    private String username;
    private String oldPassword;
    private String newPassword;

}
