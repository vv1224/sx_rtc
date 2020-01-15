package com.ddthree.sx_rtc.service.impl;

import com.ddthree.sx_rtc.dao.IUserDao;
import com.ddthree.sx_rtc.dao.entity.User;
import com.ddthree.sx_rtc.engine.SXChatEngine;
import com.ddthree.sx_rtc.exception.SXException;
import com.ddthree.sx_rtc.service.IUserService;
import me.xuxiaoxiao.xtools.common.XTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;

/**
 * 请填写类的描述
 * <ul>
 * <li>[2020/1/15 12:57]XXX：初始创建</li>
 * </ul>
 *
 * @author XXX
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private SXChatEngine sxChatEngine;

    @Nonnull
    @Override
    public String userLogin(@Nonnull String username, @Nonnull String password, @Nonnull String platform) {
        if (XTools.strEmpty(username) || XTools.strEmpty(password)) {
            throw new SXException(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误");
        } else {
            User query = new User();
            query.setUsername(username);
            query.setPassword(XTools.md5(password));

            User result = userDao.selectOne(query);
            if (result == null) {
                throw new SXException(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误");
            } else {
                sxChatEngine.login(result.getUsername());
                return result.getUsername();
            }
        }
    }
}
