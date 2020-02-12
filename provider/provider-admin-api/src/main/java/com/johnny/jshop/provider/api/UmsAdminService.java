package com.johnny.jshop.provider.api;

import com.johnny.jshop.provider.domain.UmsAdmin;

/**
 * 用户管理服务
 * <p>
 * Description: 
 * </p>
 *
 * @class UmsAdminService
 * @author JohnnyHao
 * @date 2020-02-11 
 */ 
public interface UmsAdminService{
    
    /** 
     * 新增用户
     * @Param umsAdmin: 
     * @return: int 
     * @author: JohnnyHao
     * @date: 2020-02-11 
     */ 
    int insert(UmsAdmin umsAdmin);

    /**
     * 获取用户
     * @Param username:
     * @return: com.johnny.jshop.provider.domain.UmsAdmin
     * @author: JohnnyHao
     * @date: 2020-02-11
     */
    UmsAdmin get(String username);

    /**
     * 获取用户
     * @Param umsAdmin:
     * @return: com.johnny.jshop.provider.domain.UmsAdmin
     * @author: JohnnyHao
     * @date: 2020-02-11
     */
    UmsAdmin get(UmsAdmin umsAdmin);

}
