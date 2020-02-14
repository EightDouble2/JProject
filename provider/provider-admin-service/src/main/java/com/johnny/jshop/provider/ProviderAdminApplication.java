package com.johnny.jshop.provider;

import com.johnny.jshop.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackageClasses = {ProviderAdminApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.johnny.jshop.provider.mapper")
public class ProviderAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminApplication.class, args);
    }
}
