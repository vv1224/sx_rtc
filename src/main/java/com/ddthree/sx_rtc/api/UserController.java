package com.ddthree.sx_rtc.api;

import com.ddthree.sx_rtc.engine.SXChatEngine;
import com.ddthree.sx_rtc.service.IUserService;
import com.ddthree.sx_rtc.utils.XResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 * <ul>
 * <li>[2020/1/15 12:39]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private SXChatEngine sxChatEngine;

    @PostMapping("/login")
    public XResult<String> userLogin(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "platform", defaultValue = "browser") String platform) {
        return XResult.success(userService.userLogin(username, password, platform));
    }

    @PostMapping("/logout")
    public XResult<String> userLogin(@RequestParam("addr") String addr) {
        sxChatEngine.logout(addr);
        return XResult.success("success");
    }
}
