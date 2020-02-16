package com.johnny.jshop.provider.service;

import javax.annotation.Resource;

import com.johnny.jshop.provider.domain.UmsAdminLoginLog;
import com.johnny.jshop.provider.mapper.UmsAdminLoginLogMapper;
import com.johnny.jshop.provider.api.UmsAdminLoginLogService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
