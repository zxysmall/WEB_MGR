package com.xy.rest.modular.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xy.rest.common.SimpleObject;

/**
 * 常规控制器
 *
 * @author tom
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello(@RequestBody SimpleObject simpleObject) {
        System.out.println(simpleObject.getUser());
        return ResponseEntity.ok("请求成功!");
    }
}
