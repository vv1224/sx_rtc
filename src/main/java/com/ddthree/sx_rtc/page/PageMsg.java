package com.ddthree.sx_rtc.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 18:58]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Controller
public class PageMsg {

    @GetMapping("/msg.page")
    public ModelAndView msgView(@RequestParam("addr") String addr) {
        ModelAndView modelAndView = new ModelAndView("/msg");
        modelAndView.addObject("addr", addr);
        return modelAndView;
    }
}
