package com.xy.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import com.xy.core.node.ZTreeNode;

import java.util.List;
import java.util.Map;

/**
 * 部门dao
 *
 * @author tom
 * @date 2017年2月17日20:28:58
 */
public interface DeptDao {

    /**
     * 获取ztree的节点列表
     *
     * @return
     * @date 2017年2月17日 下午8:28:43
     */
    List<ZTreeNode> tree();
    
    /**
     * 获取ztree的节点列表
     * 
     * @param roleId 角色id
     * @return 
     */
    List<ZTreeNode> treeByRoleId(Integer roleId);

    List<Map<String, Object>> list(@Param("condition") String condition);
}
