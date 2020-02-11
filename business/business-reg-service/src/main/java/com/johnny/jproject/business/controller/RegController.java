package com.johnny.jproject.business.controller;

import com.johnny.jproject.commons.dto.ResponseResult;
import com.johnny.jproject.provider.api.UmsAdminService;
import com.johnny.jproject.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册.
 * <p>
 * Description:
 * </p>
 *
 * @class RegController
 * @author JohnnyHao
 * @date 2020-02-11
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /** 
     * 注册.
     * @Param umsAdmin: 
     * @return: com.johnny.jproject.commons.dto.ResponseResult<com.johnny.jproject.provider.domain.UmsAdmin> 
     * @author: JohnnyHao
     * @date: 2020-02-11 
     */ 
    @PostMapping(value = "")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin) {
        String message = validateReg(umsAdmin);

        // 通过验证
        if (message == null) {
            int result = umsAdminService.insert(umsAdmin);

            if (result > 0) {
                UmsAdmin umsAdminInfo = umsAdminService.get(umsAdmin.getUsername());
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(), "用户注册成功", umsAdminInfo);
            }
        }

        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(), message != null ? message : "用户注册失败");
    }

    /**
     * 注册信息验证
     * @Param umsAdmin:
     * @return: java.lang.String
     * @author: JohnnyHao
     * @date: 2020-02-11
     */
    private String validateReg(UmsAdmin umsAdmin) {
        UmsAdmin umsAdminInfo = umsAdminService.get(umsAdmin.getUsername());

        if (umsAdminInfo != null) {
            return "用户名已存在，请重新输入";
        }

        return null;
    }

}
