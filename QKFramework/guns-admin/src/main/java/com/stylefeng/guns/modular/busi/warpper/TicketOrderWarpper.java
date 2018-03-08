package com.stylefeng.guns.modular.busi.warpper;

import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

public class TicketOrderWarpper extends BaseControllerWarpper {

	public TicketOrderWarpper(Object obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if("0".equals(map.get("status")+"")){
			map.put("status","已出");
		}else{
			map.put("status","未出");
		}
		
		try {
			Integer createUserId = Integer.valueOf(map.get("createUserid").toString());
			map.put("createUser", ConstantFactory.me().getUserNameById(createUserId));
		} catch (Exception e) {
			map.put("createUser", "");
		}
		try {
			Integer updateUserId = Integer.valueOf(map.get("updateUserid").toString());
			map.put("updateUser", ConstantFactory.me().getUserNameById(updateUserId));
		} catch (Exception e) {
			map.put("updateUser", "");
		}
	}

}
