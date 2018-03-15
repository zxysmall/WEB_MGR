package com.xy.system;

import com.xy.base.BaseJunit;
import com.xy.common.persistence.dao.UserMapper;
import com.xy.modular.system.dao.UserMgrDao;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * 用户测试
 *
 * @author tom
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMgrDao userMgrDao;

    @Resource
    UserMapper userMapper;

    @Test
    public void userTest() throws Exception {

    }

}
