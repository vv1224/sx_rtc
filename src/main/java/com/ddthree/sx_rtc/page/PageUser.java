package com.ddthree.sx_rtc.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 18:53]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Controller
public class PageUser {

    @RequestMapping("/")
    public void home(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "/login.page");
    }

    @RequestMapping("/login.page")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }

}
