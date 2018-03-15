package com.stylefeng.guns.modular.busi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.IUserMgrService;

@Controller
public class BusiController extends BaseController {

	 @Autowired
	 private IUserMgrService userMgrService; 
	 
	 /**
	  * 数据权限管控
	 * @param userId 用户要查询的userID
	 * @return
	 */
	List<Integer> dataControl(String userId) {
			List<Integer> users = new ArrayList<>();
	    	Integer _userId = ConstantFactory.me().getIdByUserName(userId);
	    	List<Integer> usersDC = userMgrService.selectUsers();
	    	if(null != usersDC){
	    		if(null != _userId && !usersDC.contains(_userId)){
	    			return null;
	    		}
	    		if(null != _userId){
	    			users.add(_userId);
	    		}else{
	    			users.addAll(usersDC);
	    		}
	    	}else if(null != _userId){
	    		users.add(_userId);
	    	}else{
	    		users = null;
	    	}
			return users;
		}
}
