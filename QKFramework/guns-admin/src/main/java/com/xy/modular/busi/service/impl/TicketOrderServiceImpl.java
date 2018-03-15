package com.stylefeng.guns.modular.busi.service.impl;

import com.stylefeng.guns.common.persistence.model.TicketOrder;
import com.stylefeng.guns.common.persistence.dao.TicketOrderMapper;
import com.stylefeng.guns.modular.busi.dao.TicketOrderDao;
import com.stylefeng.guns.modular.busi.service.ITicketOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-02-25
 */
@Service
public class TicketOrderServiceImpl extends ServiceImpl<TicketOrderMapper, TicketOrder> implements ITicketOrderService {
	
	@Resource
	TicketOrderDao ticketOrderDao;
	
}
