package com.xsq.lagou.filter;

import com.xsq.lagou.common.UserCommonInfo;
import com.xsq.lagou.exception.NoAuthorityException;
import com.xsq.lagou.util.CookieUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SessionFilter
 * @Description TODO
 * @Author xsq
 * @Date 2020/5/12 15:51
 **/
public class SessionFilter implements Filter {
    /**
     * 登录接口直接跳过过滤
     */
    private final static String LOGIN_URL = "/user/login.do";
    public static String[] default_login_action = {"/user/login", "/view/login", "index", "/view/error"};

    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (ctx != null && ctx.getBean("redisTemplate") != null && redisTemplate == null) {
            redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取访问地址后缀
        String requestURL = request.getRequestURI();
        //放过访问 jss 与css静态请求
        if (requestURL.contains(".css") || requestURL.contains(".js") || requestURL.contains("/error") || requestURL.contains(".woff2")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String name = (String) request.getSession().getAttribute(UserCommonInfo.USER_INFO);
        System.out.println("session内容：" + name);
        for (String url : default_login_action) {
            //如果访问登录页面直接跳过
            if (requestURL.endsWith(url)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        if (redisSessionKey == null) {
            response.sendRedirect("/view/login");
        }
        String nameSpace = UserCommonInfo.SESSION + UserCommonInfo.ContextNameSpace + UserCommonInfo.USER_INFO;
        String userSessionString = (String) redisTemplate.opsForHash().get(nameSpace, nameSpace + redisSessionKey);
        if (userSessionString != null) {
            redisTemplate.expire(nameSpace, 1800L, TimeUnit.SECONDS);
           /* request.getSession().setAttribute(UserCommonInfo.USER_INFO, userSessionString);*/
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (userSessionString == null) {
            response.sendRedirect("/view/login");
        }

    }

    @Override
    public void destroy() {

    }
}
