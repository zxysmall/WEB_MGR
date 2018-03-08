package com.stylefeng.guns.modular.busi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author tomzhou123
 * @since 2018-02-25
 */
public interface TicketOrderDao{
	
    /**
     * 根据条件查询订单列表
     * @param users
     * @return
     */
    List<Map<String, Object>> selectTicketOrder(@Param("users") List<Integer> users,
    		@Param("status") Integer status,
    		@Param("beginTime") String beginTime, @Param("endTime") String endTime);
    
    /**
     * 出票
     * @param orderId 订单ID
     * @param ticketNum 出票号
     * @param operUserId 操作用户ID
     * @return
     */
    int outTicket(@Param("orderId") Integer orderId,@Param("ticketNum")String ticketNum,
    		@Param("operUserId")Integer operUserId);
}
