package com.johnny.jshop.cloud.feign;

import com.johnny.jshop.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * <p>
 * Description:
 * </p>
 *
 * @class UploadFeign
 * @author JohnnyHao
 * @date 2020-02-13
 */ 
@FeignClient(value = "cloud-upload", path = "upload", configuration = FeignRequestConfiguration.class)
public interface UploadFeign {

    /**
     * 文件上传
     * @Param multipartFile:
     * @return: java.lang.String
     * @author: JohnnyHao
     * @date: 2020-02-13
     */
    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);
}
