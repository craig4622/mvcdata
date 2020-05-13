package com.xsq.lagou.controller;

import com.xsq.lagou.common.ResultPack;
import com.xsq.lagou.common.UserCommonInfo;
import com.xsq.lagou.config.ServerConfig;
import com.xsq.lagou.pojo.Resume;
import com.xsq.lagou.service.ResumeService;
import com.xsq.lagou.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 16:03
 **/
@Controller
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ServerConfig serverConfig;

    public String getUrl() {
        return serverConfig.getUrl();
    }

    @Autowired
    private ResumeService resumeService;

    /**
     * 增加用户
     *
     * @param resume
     */
    @RequestMapping("/addNewResume")
    @ResponseBody
    public ResultPack addNewResume(@RequestBody Resume resume,HttpServletRequest request) throws Exception {
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        System.out.println("------服务器1的addNewResume的访问地址：" + getUrl() + "服务器的sessionid：" + redisSessionKey);
        resumeService.addNewResume(resume);
        return ResultPack.success();
    }

    /**
     * 修改用户
     *
     * @param resume
     */
    @RequestMapping("/updateResume")
    @ResponseBody
    public ResultPack updateResume(@RequestBody Resume resume,HttpServletRequest request) {
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        System.out.println("------服务器1的updateResume的访问地址：" + getUrl() + "服务器的sessionid：" + redisSessionKey);
        resumeService.updateResume(resume);
        return ResultPack.success();
    }

    /**
     * 查询所有用户
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public ResultPack queryAll(HttpServletRequest request) throws Exception {
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        System.out.println("------服务器1的queryAll的访问地址：" + getUrl() + "服务器的sessionid：" + redisSessionKey);
        return ResultPack.success(resumeService.queryResumeList());
    }

    /**
     * 删除指定用户
     */
    @RequestMapping("/delResume")
    @ResponseBody
    public ResultPack delResume(Long id, HttpServletRequest request) throws Exception {
        String redisSessionKey = CookieUtil.getCookie(UserCommonInfo.REDIS_SESSION_ID, request);
        System.out.println("------服务器1的delResume的访问地址：" + getUrl() + "服务器的sessionid：" + redisSessionKey);
        resumeService.delResume(id);
        return ResultPack.success();
    }


}
