package com.xy.modular.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xy.common.constant.factory.ConstantFactory;
import com.xy.common.persistence.dao.DataControlMapper;
import com.xy.common.persistence.model.DataControl;
import com.xy.common.persistence.model.RoleDataControl;
import com.xy.core.datascope.DataScope;
import com.xy.core.shiro.ShiroKit;
import com.xy.modular.system.dao.UserMgrDao;
import com.xy.modular.system.service.IDataControlService;
import com.xy.modular.system.service.IRoleDataControlService;
import com.xy.modular.system.service.IUserMgrService;

/**
 * <p>
 * 用户管理表 服务实现类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-01
 */
@Service("userMgrService")
public class UserMgrServiceImpl  extends ServiceImpl<DataControlMapper, DataControl> implements IUserMgrService {
	
    @Resource
    private UserMgrDao managerDao;
    
    @Resource
    private IDataControlService dataControlService;
    
    @Resource
    private IRoleDataControlService roleDataControlService;
    
    /**
     * 针对分配数据权限的用户
     * 
     * 查询数据权限范围内的所有用户
     *
     * @return
     */
    public List<Integer> selectUsers(){
    	List<Integer> result = new ArrayList<>();
    	List<Integer> orgSelect = new ArrayList<>();
    	Integer roleSelect =  null;
	    Integer currDept = ShiroKit.getUser().getDeptId();
    	Integer currRoleId =  ShiroKit.getUser().getRoleList().get(0);
    	RoleDataControl rdc = roleDataControlService.selectOne(new EntityWrapper<RoleDataControl>().eq("role_id", currRoleId));
    	if(null == rdc){
    		return null;
    	}
    	int dataType = rdc.getDataType();
    	DataScope dataScope = null;
    	switch (dataType) {
			case 1://全部
				return null;
			case 2://分管组织/部门
				orgSelect = ConstantFactory.me().getDataControlOrg(currRoleId);
				if(!orgSelect.isEmpty()){
					orgSelect.remove(currDept);
					dataScope = new DataScope(orgSelect);
					break;
				}
			case 3://所属组织/部门
				orgSelect.add(currDept);
				dataScope = new DataScope(orgSelect);
				break;
			case 4://所属角色
				roleSelect = currRoleId;
				break;
			default://5:个人
				result.add(ShiroKit.getUser().getId());
				return result;
		}
    	List<Map<String, Object>> users = managerDao.selectUsers(dataScope, null, null, null, roleSelect);
    	for (Map<String, Object> map : users) {
    		result.add(Integer.valueOf(map.get("id").toString()));
		}
    	return result;
    }

}
