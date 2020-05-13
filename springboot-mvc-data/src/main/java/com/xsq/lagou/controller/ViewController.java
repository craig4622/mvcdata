package com.xsq.lagou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ViewController
 * @Description TODO
 * @Author xsq
 * @Date 2020/5/12 9:59
 **/
@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/index")
    public String index(ModelAndView modelAndView) {
        return "index";
    }

    @RequestMapping("/login")
    public String login(ModelAndView modelAndView) {
        return "xm/login";
    }


    @RequestMapping("/list")
    public String list(ModelAndView modelAndView) {
        return "xm/list";
    }

    @RequestMapping("/error")
    public String error(ModelAndView modelAndView) {
        return "xm/error";
    }
}
