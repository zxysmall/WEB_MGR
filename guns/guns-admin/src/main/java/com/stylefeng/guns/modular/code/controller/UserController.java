package com.stylefeng.guns.modular.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="用户controller",tags={"用户操作接口"})
@RestController
public class UserController {
	
     @ApiOperation(value="获取用户信息",tags={"查询"},notes="注意问题点")
     @GetMapping("/getUserInfo")
     public User getUserInfo(@RequestParam @ApiParam(name="id",value="用户id") Long id,
    		 @RequestParam @ApiParam(name="username",value="用户名") String username) {
      // userService可忽略，是业务逻辑
      User user =new User();

      return user;
     }
     
     @ApiOperation(value="更改用户信息",tags={"修改"},notes="注意问题点")
     @PostMapping("/updateUserInfo")
     public int updateUserInfo(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user){

        int num = 1;
        return num;
     }
     
     @ApiOperation(value="查询测试",tags={"查询"},notes="注意问题点")
     @GetMapping("select")
     //@ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query")
     @ApiImplicitParams({
     @ApiImplicitParam(name="name",value="用户名",dataType="String", paramType = "query"),
     @ApiImplicitParam(name="id",value="用户id",dataType="Long", paramType = "query")})
     public void select(String name,Long id){
    	 System.out.println();
     }
}
