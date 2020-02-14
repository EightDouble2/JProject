package com.johnny.jshop.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.johnny.jshop.business.controller.fallback.ProfileControllerFallback;
import com.johnny.jshop.business.dto.UmsAdminDTO;
import com.johnny.jshop.business.dto.params.IconParam;
import com.johnny.jshop.business.dto.params.PasswordParam;
import com.johnny.jshop.business.dto.params.ProfileParam;
import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.provider.api.UmsAdminService;
import com.johnny.jshop.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 个人信息管理
 * <p>
 * Description:
 * </p>
 *
 * @class ProfileController
 * @author JohnnyHao
 * @date 2020-02-13
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 获取个人信息
     * @Param username:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<com.johnny.jshop.provider.domain.UmsAdmin>
     * @author: JohnnyHao
     * @date: 2020-02-13
     */
    @GetMapping(value = "/info/{username}")
    @SentinelResource(value = "info", fallback = "infoFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<UmsAdminDTO> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.get(username);
        UmsAdminDTO dto = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdmin, dto);
        return new ResponseResult<UmsAdminDTO>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), dto);
    }

    /**
     * 更新个人信息
     * @Param profileParam:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<java.lang.Void>
     * @author: JohnnyHao
     * @date: 2020-02-13
     */
    @PostMapping(value = "/update")
    public ResponseResult<Void> update(@RequestBody ProfileParam profileParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(profileParam, umsAdmin);
        int result = umsAdminService.update(umsAdmin);

        if (result > 0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), null);
        }

        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL.value(), ResponseResult.CodeStatus.FAIL.getReasonPhrase(), null);
        }

    }

    /** 
     * 修改密码
     * @Param passwordParam: 
     * @return: com.johnny.jshop.commons.dto.ResponseResult<java.lang.Void> 
     * @author: JohnnyHao
     * @date: 2020-02-14 
     */ 
    @PostMapping(value = "modify/password")
    public ResponseResult<Void> modifyPassword(@RequestBody PasswordParam passwordParam) {
        UmsAdmin umsAdmin = umsAdminService.get(passwordParam.getUsername());

        // 旧密码正确
        if (bCryptPasswordEncoder.matches(passwordParam.getOldPassword(), umsAdmin.getPassword())) {
            int result = umsAdminService.modifyPassword(umsAdmin.getUsername(), passwordParam.getNewPassword());
            if (result > 0) {
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase());
            }
        }

        // 旧密码错误
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL.value(), ResponseResult.CodeStatus.FAIL.getReasonPhrase());
        }

        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL.value(), ResponseResult.CodeStatus.FAIL.getReasonPhrase());
    }

    /** 
     * 修改头像
     * @Param iconParam: 
     * @return: com.johnny.jshop.commons.dto.ResponseResult<java.lang.Void> 
     * @author: JohnnyHao
     * @date: 2020-02-14 
     */ 
    @PostMapping(value = "modify/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody IconParam iconParam) {
        int result = umsAdminService.modifyIcon(iconParam.getUsername(), iconParam.getPath());

        // 成功
        if (result > 0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase());
        }

        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL.value(), ResponseResult.CodeStatus.FAIL.getReasonPhrase());
        }
    }

}
