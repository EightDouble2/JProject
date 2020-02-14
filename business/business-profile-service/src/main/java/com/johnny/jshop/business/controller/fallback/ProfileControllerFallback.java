package com.johnny.jshop.business.controller.fallback;

import com.johnny.jshop.business.dto.UmsAdminDTO;
import com.johnny.jshop.business.feign.fallback.ProfileFeignFallback;
import com.johnny.jshop.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户管理服务熔断器
 * <p>
 * Description:
 * </p>
 *
 * @class ProfileControllerFallback
 * @author JohnnyHao
 * @date 2020-02-14
 */
public class ProfileControllerFallback {

    private static final Logger logger = LoggerFactory.getLogger(ProfileControllerFallback.class);

    /**
     * 熔断方法
     * @Param username:
     * @Param ex:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<com.johnny.jshop.business.dto.UmsAdminDTO>
     * @author: JohnnyHao
     * @date: 2020-02-14
     */
    public static ResponseResult<UmsAdminDTO> infoFallback(String username, Throwable ex) {
        logger.warn("Invoke infoFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<UmsAdminDTO>(ResponseResult.CodeStatus.BREAKING.value(), ProfileFeignFallback.BREAKING_MESSAGE);
    }

}
