package com.johnny.jshop.cloud.controller;

import com.johnny.jshop.cloud.dto.UmsAdminLoginLogDTO;
import com.johnny.jshop.cloud.producer.MessageProducer;
import com.johnny.jshop.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Resource
    private MessageProducer messageProducer;

    @PostMapping(value = "admin/login/log")
    public ResponseResult<Void> sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto) {
        boolean flag = messageProducer.sendAdminLoginLog(dto);
        if (flag) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK.value(), "发送管理员登录日志成功");
        }

        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL.value(), "发送管理员登录日志失败");
        }
    }
}
