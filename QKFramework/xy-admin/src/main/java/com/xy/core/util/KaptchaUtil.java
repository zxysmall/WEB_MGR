package com.xy.core.util;

import com.xy.config.properties.XyProperties;
import com.xy.core.util.SpringContextHolder;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     *
     * @author tom
     * @Date 2017/5/23 22:34
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(XyProperties.class).getKaptchaOpen();
    }
}