package com.ddthree.sx_rtc.engine;

import org.junit.Test;

import java.util.Arrays;

public class SXChatEngineImplTest {

    @Test
    public void test() {
        SXChatEngineImpl engine = new SXChatEngineImpl();
        SXUser user1 = new SXUser("xxx");
        SXUser user2 = new SXUser("sw");

        engine.login(user1);
        engine.login(user2);
        SXMsg msg = new SXMsg(user1.getId(), user2.getId(), "孙伟是个大傻叼");
        engine.pushMsg(msg);

        System.out.println(Arrays.toString(engine.pullMsg(user2, 3000)));
        System.out.println(Arrays.toString(engine.pullMsg(user1, 3000)));
    }
}