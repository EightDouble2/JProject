package com.johnny.jshop.provider.service.fallback;

import com.johnny.jshop.provider.domain.UmsAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户服务提供者熔断器
 * <p>
 * Description:
 * </p>
 *
 * @class UmsAdminServiceFallback
 * @author JohnnyHao
 * @date 2020-02-14
 */
public class UmsAdminServiceFallback {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceFallback.class);

    /**
     * 熔断方法
     * @Param username:
     * @Param ex:
     * @return: com.johnny.jshop.provider.domain.UmsAdmin
     * @author: JohnnyHao
     * @date: 2020-02-14
     */
    public static UmsAdmin getByUsernameFallback(String username, Throwable ex) {
        logger.warn("Invoke getByUsernameFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return null;
    }

}
