package com.xy.rest.modular.auth.security.impl;

import org.springframework.util.Base64Utils;

import com.xy.rest.modular.auth.security.DataSecurityAction;

/**
 * 对数据进行base64编码的方式
 *
 * @author tom
 * @date 2017-09-18 20:43
 */
public class Base64SecurityAction implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }
}
