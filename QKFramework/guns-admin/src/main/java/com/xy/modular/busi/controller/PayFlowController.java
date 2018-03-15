package com.stylefeng.guns.modular.busi.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.PayFlow;
import com.stylefeng.guns.modular.busi.service.IPayFlowService;

/**
 * 支付监控控制器
 *  模拟订单支付的执行流程(工作流的形式跑订单)
 * @author tomzhou
 * @Date 2018-03-13 16:15:42
 */
@Controller
@RequestMapping("/payFlow")
public class PayFlowController extends BaseController {

    private String PREFIX = "/busi/payFlow/";

    @Autowired
    private IPayFlowService payFlowService;

    /**
     * 跳转到支付监控首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payFlow.html";
    }

    /**
     * 跳转到添加支付监控
     */
    @RequestMapping("/payFlow_add")
    public String payFlowAdd() {
        return PREFIX + "payFlow_add.html";
    }

    /**
     * 跳转到修改支付监控
     */
    @RequestMapping("/payFlow_update/{payFlowId}")
    public String payFlowUpdate(@PathVariable Integer payFlowId, Model model) {
        PayFlow payFlow = payFlowService.selectById(payFlowId);
        model.addAttribute("item",payFlow);
        LogObjectHolder.me().set(payFlow);
        return PREFIX + "payFlow_edit.html";
    }

    /**
     * 获取支付监控列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return payFlowService.selectList(null);
    }

    /**
     * 新增支付监控
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PayFlow payFlow) {
        payFlowService.add(payFlow);
        return SUCCESS_TIP;
    }

    /**
     * 删除支付监控
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer payFlowId) {
        payFlowService.deleteById(payFlowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改支付监控
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PayFlow payFlow) {
        payFlowService.updateById(payFlow);
        return SUCCESS_TIP;
    }

    /**
     * 支付监控详情
     */
    @RequestMapping(value = "/detail/{payFlowId}")
    @ResponseBody
    public Object detail(@PathVariable("payFlowId") Integer payFlowId) {
        return payFlowService.selectById(payFlowId);
    }
    
    /**
     * 查看当前流程图
     */
    @RequestMapping("/payFlow_view/{payFlowId}")
    public void expenseView(@PathVariable Integer payFlowId) {
        try {
        	payFlowService.printProcessImage(payFlowId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 审核通过
     */
    @RequestMapping(value = "/excute")
    @ResponseBody
    public Object excute(String insId) {
    	payFlowService.pass(insId);
        return SUCCESS_TIP;
    }
}
