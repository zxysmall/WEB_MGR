package com.stylefeng.guns.modular.system.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.dao.RelationMapper;
import com.stylefeng.guns.common.persistence.dao.RoleMapper;
import com.stylefeng.guns.common.persistence.model.DataControl;
import com.stylefeng.guns.common.persistence.model.Relation;
import com.stylefeng.guns.common.persistence.model.RoleDataControl;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.modular.system.dao.RoleDao;
import com.stylefeng.guns.modular.system.service.IDataControlService;
import com.stylefeng.guns.modular.system.service.IRoleDataControlService;
import com.stylefeng.guns.modular.system.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleDao roleDao;

    @Resource
    RelationMapper relationMapper;
    
    @Resource
    IRoleDataControlService roleDataControlService;
    
    @Resource
    IDataControlService dataControlService;

    @Override
    @Transactional(readOnly = false)
    public void setAuthority(Integer roleId, String ids) {
    	
    	// 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);

        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    	Map<String, Object> setRdc = roleDataControlService.selectMap(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
    	// 删除该角色所有的数据权限
    	if(StringUtils.isEmpty(ids)){
    		dataControlService.delete(new EntityWrapper<DataControl>().eq("role_id", roleId));
    		roleDataControlService.delete(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
    	}else{
    		int count  = roleDataControlService.selectCount(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
    		if(count <= 0){
    			//添加默认数据权限
    			if(null == setRdc || setRdc.isEmpty()){
    				RoleDataControl rdc = new RoleDataControl();
    				rdc.setRoleId(roleId);
    				rdc.setDataType(5);
    				roleDataControlService.insert(rdc);
    			}
    		}
    	}
    }
    

	@Override
	@Transactional(readOnly = false)
	public void setDataAuthority(Integer roleId, Integer dataType, String ids) {
		// TODO Auto-generated method stub
		dataControlService.delete(new EntityWrapper<DataControl>().eq("role_id", roleId));
		if(dataType == 2){
			if(!StringUtils.isEmpty(ids)){
				// 添加新的权限
				for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
					DataControl dc = new DataControl();
					dc.setRoleId(roleId);
					dc.setOrgId(id.intValue());
					dataControlService.insert(dc);
				}
			}
		}
		roleDataControlService.delete(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
		RoleDataControl rdc = new RoleDataControl();
		rdc.setRoleId(roleId);
		rdc.setDataType(dataType);
		roleDataControlService.insert(rdc);
	}

    @Override
    @Transactional(readOnly = false)
    public void delRoleById(Integer roleId) {
    	// 删除该角色所有的数据权限
    	Map<String, Object> setRdc = roleDataControlService.selectMap(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
    	if(null != setRdc && !setRdc.isEmpty()){
    		if(null != setRdc.get("dataControlId")){
    			int dataControlId = Integer.valueOf(setRdc.get("dataControlId").toString());
    			dataControlService.delete(new EntityWrapper<DataControl>().eq("id", dataControlId));
    		}
    		roleDataControlService.delete(new EntityWrapper<RoleDataControl>().eq("role_id", roleId));
    	}
    	// 删除该角色所有的权限
    	this.roleDao.deleteRolesById(roleId);
    	
        //删除角色
        this.roleMapper.deleteById(roleId);
    }

}
