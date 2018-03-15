package com.xy.modular.busi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.common.persistence.model.TicketOrder;
import com.xy.core.base.tips.ErrorTip;
import com.xy.core.base.tips.Tip;
import com.xy.core.log.LogObjectHolder;
import com.xy.core.shiro.ShiroKit;
import com.xy.modular.busi.dao.TicketOrderDao;
import com.xy.modular.busi.service.ITicketOrderService;
import com.xy.modular.busi.warpper.TicketOrderWarpper;

/**
 * 订单管理控制器
 *
 * @author tom
 * @Date 2018-02-25 10:41:17
 */
@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController extends BusiController {

    private String PREFIX = "/busi/ticketOrder/";

    @Autowired
    private ITicketOrderService ticketOrderService;

    @Autowired
    private TicketOrderDao orderDao; 
    
    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ticketOrder.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/ticketOrder_add")
    public String ticketOrderAdd() {
        return PREFIX + "ticketOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/ticketOrder_update/{ticketOrderId}")
    public String ticketOrderUpdate(@PathVariable Integer ticketOrderId, Model model) {
        TicketOrder ticketOrder = ticketOrderService.selectById(ticketOrderId);
        model.addAttribute("item",ticketOrder);
        LogObjectHolder.me().set(ticketOrder);
        return PREFIX + "ticketOrder_edit.html";
    }
    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/ticketOrder_outTicket/{ticketOrderId}")
    public String ticketOrderOutTicket(@PathVariable Integer ticketOrderId, Model model) {
    	TicketOrder ticketOrder = ticketOrderService.selectById(ticketOrderId);
    	model.addAttribute("item",ticketOrder);
    	LogObjectHolder.me().set(ticketOrder);
    	return PREFIX + "ticketOrder_outticket.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, 
    		@RequestParam(required = false) Integer status, 
    		@RequestParam(required = false) String beginTime,
    		@RequestParam(required = false) String endTime) {
    	List<Integer> users = dataControl(name);
    	if(null != status){
    		status -= 2;
    	}
        return new TicketOrderWarpper(orderDao.selectTicketOrder(users,status,beginTime,endTime)).warp();
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TicketOrder ticketOrder) {
    	if(null != ticketOrder){
    		ticketOrder.setCreateUserid(ShiroKit.getUser().getId());
    		ticketOrder.setStatus(1);
    	}
        ticketOrderService.insert(ticketOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ticketOrderId) {
    	Tip tip	 = validateOrder(ticketOrderId);
    	if(tip == null){
    		ticketOrderService.deleteById(ticketOrderId);
    	}else{
    		return tip;
    	}
        return SUCCESS_TIP;
    }

	private Tip validateOrder(Integer ticketOrderId) {
		TicketOrder to = ticketOrderService.selectById(ticketOrderId);
    	if(null != to){
    		if(to.getStatus()==0){
    			return new ErrorTip(601,"订单已出票");
    		}
    	}else{
    		return new ErrorTip(600,"订单不存在");
    	}
    	return null;
	}

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TicketOrder ticketOrder) {
    	Tip tip	 = validateOrder(ticketOrder.getId());
    	if(tip == null){
    		ticketOrderService.updateById(ticketOrder);
    	}else{
    		return tip;
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 出票
     */
    @RequestMapping(value = "/outTicket")
    @ResponseBody
    public Object outTicket(TicketOrder ticketOrder) {
    	Tip tip	 = validateOrder(ticketOrder.getId());
    	if(tip == null){
    		orderDao.outTicket(ticketOrder.getId(), ticketOrder.getTicketNum(), ShiroKit.getUser().getId());
    	}else{
    		return tip;
    	}
    	return SUCCESS_TIP;
    }
    
    

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{ticketOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("ticketOrderId") Integer ticketOrderId) {
        return ticketOrderService.selectById(ticketOrderId);
    }
}
