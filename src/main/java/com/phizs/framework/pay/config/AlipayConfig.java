/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.config;

/**
 * @author lianyutao
 * @支付宝配置文件
 * @date 2018/1/17 12:23
 * @since 2.1.0
 */

public class AlipayConfig {

    private String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key_path;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key_path;

    // 支付宝网关
    private String gatewayUrl;

    // 签名方式
    private String sign_type;

    // 字符编码格式
    private String charset;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String return_url;


    // 支付宝日志
    private String log_path;

    //参数格式
    private String format;


    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String pc_return_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String m_return_url;



    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMerchant_private_key_path() {
        return merchant_private_key_path;
    }

    public void setMerchant_private_key_path(String merchant_private_key_path) {
        this.merchant_private_key_path = merchant_private_key_path;
    }

    public String getAlipay_public_key_path() {
        return alipay_public_key_path;
    }

    public void setAlipay_public_key_path(String alipay_public_key_path) {
        this.alipay_public_key_path = alipay_public_key_path;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getPc_return_url() {
        return pc_return_url;
    }

    public void setPc_return_url(String pc_return_url) {
        this.pc_return_url = pc_return_url;
    }

    public String getM_return_url() {
        return m_return_url;
    }

    public void setM_return_url(String m_return_url) {
        this.m_return_url = m_return_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getLog_path() {
        return log_path;
    }

    public void setLog_path(String log_path) {
        this.log_path = log_path;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }



}