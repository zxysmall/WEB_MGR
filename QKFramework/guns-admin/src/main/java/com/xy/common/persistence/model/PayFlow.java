package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 支付流程跟踪
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-13
 */
@TableName("busi_pay_flow")
public class PayFlow extends Model<PayFlow> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private Integer seq;
    /**
     * （支付状态【0：支付成功；1：支付流程创建；2：支付请求发生；3:收到支付响应】）
     */
    private Integer status;
    /**
     * 订单号
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 执行类
     */
    @TableField("excuted_class")
    private String excutedClass;
    /**
     * 流程定义ID
     */
    private Integer processId;


    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getExcutedClass() {
        return excutedClass;
    }

    public void setExcutedClass(String excutedClass) {
        this.excutedClass = excutedClass;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    @Override
    protected Serializable pkVal() {
        return this.seq;
    }

    @Override
    public String toString() {
        return "PayFlow{" +
        "seq=" + seq +
        ", status=" + status +
        ", orderId=" + orderId +
        ", excutedClass=" + excutedClass +
        ", processId=" + processId +
        "}";
    }
}
