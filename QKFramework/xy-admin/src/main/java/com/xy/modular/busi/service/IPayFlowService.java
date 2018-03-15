package com.xy.modular.busi.service;

import java.io.IOException;

import com.baomidou.mybatisplus.service.IService;
import com.xy.common.persistence.model.PayFlow;

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
