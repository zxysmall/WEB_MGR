package com.xy.modular.system.service;

import java.util.List;

public interface IUserMgrService {
	
    /**
     * 根据条件查询用户列表
     *
     * @return
     */
    List<Integer> selectUsers();

}
