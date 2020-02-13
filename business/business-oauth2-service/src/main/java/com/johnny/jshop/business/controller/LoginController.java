package com.johnny.jshop.business.controller;

import com.google.common.collect.Maps;
import com.johnny.jshop.business.dto.LoginInfo;
import com.johnny.jshop.business.dto.LoginParam;
import com.johnny.jshop.business.feign.ProfileFeign;
import com.johnny.jshop.commons.dto.ResponseResult;
import com.johnny.jshop.commons.utils.MapperUtils;
import com.johnny.jshop.commons.utils.OkHttpClientUtil;
import com.johnny.jshop.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 用户登录
 * <p>
 * Description:
 * </p>
 *
 * @class LoginController
 * @author JohnnyHao
 * @date 2020-02-12
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public TokenStore tokenStore;

    @Resource
    public ProfileFeign profileFeign;

    /**
     * 登录接口
     * @Param loginParam:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: JohnnyHao
     * @date: 2020-02-12
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam) {

        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();

        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !bCryptPasswordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.FAIL.value(), ResponseResult.CodeStatus.FAIL.getReasonPhrase(), null);
        }

        // 通过http客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);
        try {
            //解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), result);
    }

    /**
     * 获取用户信息
     *
     * @return: com.johnny.jshop.commons.dto.ResponseResult<com.johnny.jshop.business.dto.LoginInfo>
     * @author: JohnnyHao
     * @date: 2020-02-12
     */
    @GetMapping(value = "/user/info")
    public ResponseResult<LoginInfo> info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String info = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(info, "data", UmsAdmin.class);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getNickName());
        loginInfo.setAvatar(umsAdmin.getIcon());

        // 封装并返回结果
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.setName(authentication.getName());
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), loginInfo);
    }

    /**
     * 用户注销
     * @Param request:
     * @return: com.johnny.jshop.commons.dto.ResponseResult<java.lang.Void>
     * @author: JohnnyHao
     * @date: 2020-02-12
     */
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        String access_token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK.value(), ResponseResult.CodeStatus.OK.getReasonPhrase(), null);
    }


}
