package com.xy.rest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Xy REST Web程序启动类
 *
 * @author tom
 * @date 2017年9月29日09:00:42
 */
public class XyServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(XyApplication.class);
    }

}
