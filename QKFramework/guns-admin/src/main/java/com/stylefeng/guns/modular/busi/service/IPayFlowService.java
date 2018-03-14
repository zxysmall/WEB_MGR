package com.stylefeng.guns.modular.busi.service;

import com.stylefeng.guns.common.persistence.model.PayFlow;

import java.io.IOException;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 支付流程跟踪 服务类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-13
 */
public interface IPayFlowService extends IService<PayFlow> {
	  public void add(PayFlow flow) ;
	  public void printProcessImage(Integer expenseId) throws IOException;
	  public void pass(String insId) ;
}
