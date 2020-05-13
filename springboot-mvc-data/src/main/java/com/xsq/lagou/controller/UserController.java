package com.xsq.lagou.controller;

import com.xsq.lagou.common.ResultPack;
import com.xsq.lagou.common.UserCommonInfo;
import com.xsq.lagou.config.ServerConfig;
import com.xsq.lagou.exception.LoginException;
import com.xsq.lagou.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 15:39
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServerConfig serverConfig;

    public String getUrl() {
        return serverConfig.getUrl();
    }

    private final static String USERNAME = "admin";

    private final static String PASSWORD = "admin";

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public ResultPack login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        System.out.println("------服务器1的login的访问地址：" + getUrl() + "服务器的sessionid：" + redisSessionKey);
        String sessionIdValue = UUID.randomUUID().toString();
        CookieUtil.setCookie(UserCommonInfo.REDIS_SESSION_ID, sessionIdValue, -1, "/", response);
        if (!USERNAME.equals(username)) {
            throw new LoginException("用户名错误");
        }
        if (!PASSWORD.equals(password)) {
            throw new LoginException("密码错误");
        }
        doSetRedisSession(request, response, username, sessionIdValue);
        return ResultPack.success();
    }

    private void doSetRedisSession(HttpServletRequest request, HttpServletResponse response, String username, String sessionIdValue) {
        /*request.getSession().setAttribute(UserCommonInfo.USER_INFO, username);*/
        String nameSpace = UserCommonInfo.SESSION + UserCommonInfo.ContextNameSpace + UserCommonInfo.USER_INFO;
        redisTemplate.opsForHash().put(nameSpace, nameSpace + sessionIdValue, username);
        redisTemplate.expire(nameSpace, 1800L, TimeUnit.SECONDS);
    }



}
