package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.RoleDataControl;
import com.stylefeng.guns.common.persistence.dao.RoleDataControlMapper;
import com.stylefeng.guns.modular.system.service.IRoleDataControlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色数据管控表 服务实现类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-01
 */
@Service("roleDataControlService")
public class RoleDataControlServiceImpl extends ServiceImpl<RoleDataControlMapper, RoleDataControl> implements IRoleDataControlService {

}
