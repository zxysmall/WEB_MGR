package com.xy.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 数据管控表
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-02
 */
@TableName("sys_data_control")
public class DataControl extends Model<DataControl> {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Integer roleId;
    /**
     * 组织ID
     */
    @TableField("org_id")
    private Integer orgId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId + this.orgId;
    }

    @Override
    public String toString() {
        return "DataControl{" +
        "roleId=" + roleId +
        ", orgId=" + orgId +
        "}";
    }
}
