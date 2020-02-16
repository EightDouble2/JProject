package com.johnny.jshop.cloud.feign.fallback;

import com.johnny.jshop.cloud.dto.UmsAdminLoginLogDTO;
import com.johnny.jshop.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

//    @Override
//    public String sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
//        return null;
//    }
}
