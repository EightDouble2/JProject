package com.johnny.jshop.cloud.api;

import com.johnny.jshop.cloud.feign.dto.UmsAdminLoginLogDTO;

public interface MessageService {

    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);

}
