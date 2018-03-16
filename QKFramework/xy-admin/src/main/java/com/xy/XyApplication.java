package com.xy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xy.config.properties.XyProperties;

/**
 * SpringBoot方式启动类
 *
 * @author tom
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
public class XyApplication extends WebMvcConfigurerAdapter {

    protected final static Logger LOGGER = LoggerFactory.getLogger(XyApplication.class);

    @Autowired
    XyProperties xyProperties;

    /**
     * 增加swagger的支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (xyProperties.getSwaggerOpen()) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    public static void main(String[] args) {
    	ApplicationContext ctx = SpringApplication.run(XyApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();  
        for (String profile : activeProfiles) {  
	    	LOGGER.info("Spring Boot 使用profile为:{}" , profile);  
	    }  
        LOGGER.info("XyApplication is success!");
    }
}
