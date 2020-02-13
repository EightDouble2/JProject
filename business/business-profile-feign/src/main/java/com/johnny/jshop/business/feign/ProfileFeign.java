package com.johnny.jshop.business.feign;

import com.johnny.jshop.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 个人信息管理Feign接口
 * <p>
 * Description:
 * </p>
 *
 * @class ProfileFeign
 * @author JohnnyHao
 * @date 2020-02-13
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {

    @GetMapping(value = "/info/{username}")
    String info(@PathVariable String username);

}
