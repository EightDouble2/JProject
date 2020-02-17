package com.johnny.jshop.cloud.feign.fallback;

import com.johnny.jshop.cloud.feign.MessageFeign;
import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

//    @Override
//    public String sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
//        try {
//            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING.value(), BREAKING_MESSAGE));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
