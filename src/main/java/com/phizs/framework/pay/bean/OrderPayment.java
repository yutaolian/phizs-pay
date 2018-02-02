/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.bean;

import java.math.BigDecimal;

/**
 * @author lianyutao
 * @自定义支付属性
 * @date 2018/1/17 14:08
 * @since 2.1.0
 */
public class OrderPayment {



    /**
     * 外部使用的跟踪编号
     */
    private String outTradeNo;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 购物小标题
     */
    private String subject;

    /**
     * 订单关闭时间（格式在支付宝官方文档）
     */
    private String timeoutExpress;
    /**
     * 支付平台代码(alipay:支付宝,wxpay:微信支付)
     */
    private String platformCode;

    /**
     * 支付平台使用的不同支付方式(如微信为,JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付|支付宝为WEBPC--电脑网站支付、WAP--手机网站支付)
     */
    private String platformSubType;

    /**
     * 订单应付金额
     */
    private BigDecimal totalAmount;

    /**
     * 支付平台保留参数
     */
    private String platformParam;

    /**
     * 异步通知地址
     */
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notifyUrl = "";

    /**
     * 同步跳转地址（微信不需要配）
     */
    private String returnUrl = "";

    private String pcReturnUrl = "";

    private String mReturnUrl = "";

    /**
     * 微信支付必须 支付机器的IP地址
     * @return
     */
    private String spbillCreateIp;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformSubType() {
        return platformSubType;
    }

    public void setPlatformSubType(String platformSubType) {
        this.platformSubType = platformSubType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPlatformParam() {
        return platformParam;
    }

    public void setPlatformParam(String platformParam) {
        this.platformParam = platformParam;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getPcReturnUrl() {
        return pcReturnUrl;
    }

    public void setPcReturnUrl(String pcReturnUrl) {
        this.pcReturnUrl = pcReturnUrl;
    }

    public String getmReturnUrl() {
        return mReturnUrl;
    }

    public void setmReturnUrl(String mReturnUrl) {
        this.mReturnUrl = mReturnUrl;
    }
}