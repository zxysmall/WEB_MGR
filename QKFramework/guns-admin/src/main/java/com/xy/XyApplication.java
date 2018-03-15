package com.stylefeng.guns;

import com.stylefeng.guns.config.properties.XyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringBoot方式启动类
 *
 * @author tom
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
public class XyApplication extends WebMvcConfigurerAdapter {

    protected final static Logger logger = LoggerFactory.getLogger(XyApplication.class);

    @Autowired
    XyProperties gunsProperties;

    /**
     * 增加swagger的支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (gunsProperties.getSwaggerOpen()) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(XyApplication.class, args);
        logger.info("GunsApplication is success!");
    }
}
