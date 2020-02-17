package com.johnny.jshop.cloud.feign;

import com.johnny.jshop.cloud.feign.fallback.MessageFeignFallback;
import com.johnny.jshop.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cloud-message", path = "message", configuration = FeignRequestConfiguration.class, fallback = MessageFeignFallback.class)
public interface MessageFeign {

//    @PostMapping(value = "admin/login/log")
//    String sendAdminLoginLog(UmsAdminLoginLogDTO dto);
}
