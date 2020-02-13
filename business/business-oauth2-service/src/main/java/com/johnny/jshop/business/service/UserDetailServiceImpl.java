package com.johnny.jshop.business.service;

import com.google.common.collect.Lists;
import com.johnny.jshop.provider.api.UmsAdminService;
import com.johnny.jshop.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义认证授权
 * <p>
 * Description:
 * </p>
 *
 * @class UserDetailServiceImpl
 * @author JohnnyHao
 * @date 2020-02-12 
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

//    private static final String USERNAME = "admin";
//    private static final String PASSWORD = "$2a$10$1/mhNAJwHh.5eQF5dz/vqOFTkxVaAY2QmPvFAXN4mq59USjOz1pXK";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        // 用户名匹配
//        if (s.equals(USERNAME)) {
//            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
//            grantedAuthorities.add(grantedAuthority);
//            return new User(USERNAME, PASSWORD, grantedAuthorities);
//        }
//
//        // 用户名不匹配
//        else {
//            return null;
//        }

        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        UmsAdmin umsAdmin = umsAdminService.get(s);

        // 账号存在
        if (umsAdmin != null) {
            return new User(umsAdmin.getUsername(), umsAdmin.getPassword(), grantedAuthorities);
        }

        else {
            return null;
        }

    }
}
