package com.ddthree.sx_rtc.engine;

import org.junit.Test;

import java.util.Arrays;

public class SXChatEngineImplTest {

    @Test
    public void test() {
        SXChatEngineImpl engine = new SXChatEngineImpl();

        engine.login("xxx");
        engine.login("sw");
        SXMsg msg = new SXMsg("xxx", "sw", "孙伟是个大傻叼");
        engine.pushMsg(new SXMsg[]{msg});

        System.out.println(Arrays.toString(engine.pullMsg("xxx", 3000)));
        System.out.println(Arrays.toString(engine.pullMsg("sw", 3000)));
    }
}