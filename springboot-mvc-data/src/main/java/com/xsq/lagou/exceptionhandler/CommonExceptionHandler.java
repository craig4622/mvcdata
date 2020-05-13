package com.xsq.lagou.exceptionhandler;

import com.xsq.lagou.exception.LoginException;
import com.xsq.lagou.exception.NoAuthorityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommonExceptionHandler
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 17:20
 **/

@ControllerAdvice
public class CommonExceptionHandler {


    /**
     * 处理自定义异常 返回指定信息
     *
     * @param re
     * @return
     */
    @ExceptionHandler({LoginException.class, NoAuthorityException.class})
    @ResponseBody
    public Map<String, Object> handleNoAutherException(final Exception re) {
        re.printStackTrace();
        Map<String, Object> rs = new HashMap<>();
        rs.put("success", false);
        rs.put("errorMessage", re.getMessage());
        return rs;
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(final Exception re) {
        re.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", re.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
