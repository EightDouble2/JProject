package com.johnny.jshop.business.feign.fallback;

import com.johnny.jshop.business.dto.params.IconParam;
import com.johnny.jshop.business.dto.params.PasswordParam;
import com.johnny.jshop.business.dto.params.ProfileParam;
import com.johnny.jshop.business.feign.ProfileFeign;
import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * 个人信息服务熔断器
 * <p>
 * Description:
 * </p>
 *
 * @class ProfileFeignFallback
 * @author JohnnyHao
 * @date 2020-02-14
 */
@Component
public class ProfileFeignFallback implements ProfileFeign {

    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    @Override
    public String info(String username) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING.value(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING.value(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyPassword(PasswordParam passwordParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING.value(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING.value(), BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
