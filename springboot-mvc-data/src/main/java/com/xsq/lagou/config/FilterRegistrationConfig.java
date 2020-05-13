package com.xsq.lagou.config;

import com.xsq.lagou.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterRegistrationConfig  注册过滤器
 * @Description TODO
 * @Author xsq
 * @Date 2020/5/12 15:52
 **/
@Configuration
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean shareSessionFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SessionFilter());
        //拦截路径
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("SessionFilter");
        //执行顺序,数字越小越早执行
        filterRegistrationBean.setOrder(-2147483598);
        return filterRegistrationBean;
    }
}
