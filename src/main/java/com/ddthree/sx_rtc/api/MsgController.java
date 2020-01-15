package com.ddthree.sx_rtc.api;

import com.ddthree.sx_rtc.engine.SXChatEngine;
import com.ddthree.sx_rtc.engine.SXMsg;
import com.ddthree.sx_rtc.utils.XResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 13:17]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@RestController
@RequestMapping("/msg/v1")
public class MsgController {

    @Autowired
    private SXChatEngine sxChatEngine;

    @PostMapping("/pull")
    public XResult<SXMsg[]> userLogin(@RequestParam("addr") String addr) {
        return XResult.success(sxChatEngine.pullMsg(addr, 30000));
    }

    @PostMapping("/push")
    public XResult<String> userLogin(@RequestParam("addrFrom") String addrFrom,
                                     @RequestParam("addrTo") String addrTo,
                                     @RequestParam(value = "content") String content) {
        sxChatEngine.pushMsg(new SXMsg[]{new SXMsg(addrFrom, addrTo, content)});
        return XResult.success("success");
    }
}
