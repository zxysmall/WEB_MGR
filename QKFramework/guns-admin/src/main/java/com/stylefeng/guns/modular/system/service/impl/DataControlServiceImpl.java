package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.DataControl;
import com.stylefeng.guns.common.persistence.dao.DataControlMapper;
import com.stylefeng.guns.modular.system.service.IDataControlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据管控表 服务实现类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-02
 */
@Service("dataControlService")
public class DataControlServiceImpl extends ServiceImpl<DataControlMapper, DataControl> implements IDataControlService {

}
