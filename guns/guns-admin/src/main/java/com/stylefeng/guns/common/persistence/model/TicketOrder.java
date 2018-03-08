package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author tomzhou123
 * @since 2018-02-25
 */
@TableName("busi_ticket_order")
public class TicketOrder extends Model<TicketOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 单票数
     */
    @TableField("single_num")
    private Integer singleNum;
    /**
     * 单优票数
     */
    @TableField("single_num_privilege")
    private Integer singleNumPrivilege;
    /**
     * 双票数
     */
    @TableField("double_num")
    private Integer doubleNum;
    /**
     * 双优票数
     */
    @TableField("double_num_privilege")
    private Integer doubleNumPrivilege;
    /**
     * 状态（0：已出；1：未出）
     */
    private Integer status;
    /**
     * 出票号
     */
    @TableField("ticket_num")
    private String ticketNum;
    /**
     * 取票人姓名
     */
    @TableField("ticket_person")
    private String ticketPerson;
    /**
     * 取票人身份证
     */
    @TableField("ticket_person_identity")
    private String ticketPersonIdentity;
    /**
     * 取票人电话
     */
    @TableField("ticket_person_phone")
    private String ticketPersonPhone;
    /**
     * 创建人
     */
    @TableField("create_userid")
    private Integer createUserid;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新人
     */
    @TableField("update_userid")
    private Integer updateUserid;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(Integer singleNum) {
        this.singleNum = singleNum;
    }

    public Integer getSingleNumPrivilege() {
        return singleNumPrivilege;
    }

    public void setSingleNumPrivilege(Integer singleNumPrivilege) {
        this.singleNumPrivilege = singleNumPrivilege;
    }

    public Integer getDoubleNum() {
        return doubleNum;
    }

    public void setDoubleNum(Integer doubleNum) {
        this.doubleNum = doubleNum;
    }

    public Integer getDoubleNumPrivilege() {
        return doubleNumPrivilege;
    }

    public void setDoubleNumPrivilege(Integer doubleNumPrivilege) {
        this.doubleNumPrivilege = doubleNumPrivilege;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getTicketPerson() {
        return ticketPerson;
    }

    public void setTicketPerson(String ticketPerson) {
        this.ticketPerson = ticketPerson;
    }

    public String getTicketPersonIdentity() {
        return ticketPersonIdentity;
    }

    public void setTicketPersonIdentity(String ticketPersonIdentity) {
        this.ticketPersonIdentity = ticketPersonIdentity;
    }

    public String getTicketPersonPhone() {
        return ticketPersonPhone;
    }

    public void setTicketPersonPhone(String ticketPersonPhone) {
        this.ticketPersonPhone = ticketPersonPhone;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TicketOrder{" +
        "id=" + id +
        ", singleNum=" + singleNum +
        ", singleNumPrivilege=" + singleNumPrivilege +
        ", doubleNum=" + doubleNum +
        ", doubleNumPrivilege=" + doubleNumPrivilege +
        ", status=" + status +
        ", ticketNum=" + ticketNum +
        ", ticketPerson=" + ticketPerson +
        ", ticketPersonIdentity=" + ticketPersonIdentity +
        ", ticketPersonPhone=" + ticketPersonPhone +
        ", createUserid=" + createUserid +
        ", createTime=" + createTime +
        ", updateUserid=" + updateUserid +
        ", updateTime=" + updateTime +
        "}";
    }
}
