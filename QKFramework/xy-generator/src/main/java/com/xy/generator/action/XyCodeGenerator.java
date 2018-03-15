package com.xy.generator.action;


import com.xy.generator.action.config.XyGeneratorConfig;

/**
 * 代码生成器,可以生成实体,dao,service,controller,html,js
 *
 * @author tom
 * @Date 2017/5/21 12:38
 */
public class XyCodeGenerator {

    public static void main(String[] args) {

        /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
         */
        XyGeneratorConfig xyGeneratorConfig = new XyGeneratorConfig();
        xyGeneratorConfig.doMpGeneration();

        /**
         * adi的生成器:
         *      adi的代码生成器可以生成controller,html页面,页面对应的js
         */
        xyGeneratorConfig.doAdiGeneration();
    }

}