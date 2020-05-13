package com.xsq.lagou.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CookieUtil
 * @Description TODO
 * @Author xsq
 * @Date 2020/5/12 16:13
 **/
public class CookieUtil {

    /**
     * 设置cookie值用于Redis实现session同步的时候获取cookie中保存的key
     *
     * @param name     cookie名称
     * @param value    cookie名称对应的值
     * @param age      过期时间,-1为永不过期除非浏览器清除
     * @param path     路径
     * @param response
     */
    public static void setCookie(String name, String value, int age, String path, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, Base64Util.base64Encoded(value));
        cookie.setMaxAge(age);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie值用于Redis实现session同步的时候获取cookie中保存的key
     *
     * @param name     cookie名称
     * @param value    cookie名称对应的值
     * @param age      过期时间,-1为永不过期除非浏览器清除
     * @param path     路径
     * @param domain   domain
     * @param response
     */
    public static void setCookie(String name, String value, int age, String path, String domain, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, Base64Util.base64Encoded(value));
        cookie.setMaxAge(age);
        cookie.setPath(path);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /***
     * 获取cookie
     * @param cookid
     * @param request
     * @return
     */
    public static String getCookie(String cookid, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String redisSessionKey = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookid)) {
                    redisSessionKey = Base64Util.base64Decoded(cookie.getValue());
                    break;
                }
            }
        }
        return redisSessionKey;
    }

}
