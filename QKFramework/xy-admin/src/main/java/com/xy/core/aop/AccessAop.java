package com.xy.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AccessAop {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final Logger ACCESS_LOGGER = LoggerFactory.getLogger("ACCESS");

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    /**
     * 在所有标注@RequestMapping的地方切入
     */
    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void beforeExec(JoinPoint joinPoint) {
        beginTime.set(System.currentTimeMillis());
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        LOGGER.info("Method :" + method.getName() + " execute ...  ");
    }

    @After("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void afterExec(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        LOGGER.info("Method :" + method.getName() + ", elapsed time : " + (System.currentTimeMillis() - beginTime.get()) + "ms");
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String uri = request.getRequestURI();
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> enums = request.getHeaderNames();
        String ele = null;
        while (enums.hasMoreElements()) {
            ele = enums.nextElement();
            paramMap.put(ele, request.getHeader(ele));
        }

        long beginTime = System.currentTimeMillis();
        // result的值就是被拦截方法的返回值
        boolean successFlag = true;
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            successFlag = false;
            //logger.error("doAround,e:{}",e);
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();

            // access日志格式
            String accessLog = uri + "|" + (successFlag ? 0 : 1) + "|" + (endTime - beginTime)
                    + "|" + paramMap;

            LOGGER.info(accessLog);
            ACCESS_LOGGER.info(accessLog);
        }
        // accessLogger.info("请求结束，controller的返回值是 " + JsonConverter.format(result));
        return result;
    }

    String getParamValue(String paramName, Map<String, String> paramMap) {
        if (StringUtils.isEmpty(paramName)) {
            return "";
        }

        String values = paramMap.get(paramName);
        if (StringUtils.isEmpty(values)) {
            return "";
        }
        return values;
    }

}
