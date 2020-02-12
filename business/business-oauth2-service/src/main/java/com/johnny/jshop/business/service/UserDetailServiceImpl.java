package com.johnny.jshop.business.service;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$1/mhNAJwHh.5eQF5dz/vqOFTkxVaAY2QmPvFAXN4mq59USjOz1pXK";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        return new User(USERNAME, PASSWORD, grantedAuthorities);
    }
}
