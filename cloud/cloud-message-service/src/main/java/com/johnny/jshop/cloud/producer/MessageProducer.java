package com.johnny.jshop.cloud.producer;

import com.johnny.jshop.cloud.api.MessageService;
import com.johnny.jshop.cloud.dto.UmsAdminLoginLogDTO;
import com.johnny.jshop.cloud.message.MessageSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息生产者
 * <p>
 * Description:
 * </p>
 *
 * @class MessageProducer
 * @author JohnnyHao
 * @date 2020-02-16
 */
@Component
@Service(version = "1.0.0")
public class MessageProducer implements MessageService {

    @Resource
    private MessageSource source;

    /**
     * 管理登录日志
     * @Param dto:
     * @return: boolean
     * @author: JohnnyHao
     * @date: 2020-02-16
     */
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }
}
