package com.johnny.jshop.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改头像参数
 * <p>
 * Description: 
 * </p>
 *
 * @class IconParam
 * @author JohnnyHao
 * @date 2020-02-14 
 */ 
@Data
public class IconParam implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像地址
     */
    private String path;

}
