package com.johnny.jshop.business.controller;

import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.provider.api.UmsAdminService;
import com.johnny.jshop.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), umsAdmin);
    }

}
