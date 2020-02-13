package com.johnny.jshop.business.controller;

import com.johnny.jshop.business.dto.ProfileParam;
import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.provider.api.UmsAdminService;
import com.johnny.jshop.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取个人信息
     * @Param username:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<com.johnny.jshop.provider.domain.UmsAdmin>
     * @author: JohnnyHao
     * @date: 2020-02-13
     */
    @GetMapping(value = "/info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), umsAdmin);
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

}
