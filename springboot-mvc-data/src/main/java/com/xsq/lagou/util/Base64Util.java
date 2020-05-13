package com.xsq.lagou.util;

import java.util.Base64;

/**
 * @ClassName Base64Util
 * @Description TODO
 * @Author xsq
 * @Date 2020/5/12 16:17
 **/
public class Base64Util {

    /**
     * Base64解密
     */
    public static String base64Encoded(String str) {
        byte[] bytes = str.getBytes();
        //Base64 加密
        String encoded = Base64.getEncoder().encodeToString(bytes);
   /*     System.out.println("Base 64 加密后：" + encoded);*/
        return encoded;
    }

    /**
     * Base64加密
     */
    public static String base64Decoded(String str) {
        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(str);
        String decodeStr = new String(decoded);
/*        System.out.println("Base 64 解密后：" + decodeStr);*/
        return decodeStr;
    }
}
