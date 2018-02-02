/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.config;

/**
 * @author lianyutao
 * @微信pay配置文件
 * @date 2018/1/17 12:23
 * @since 2.1.0
 */
public class WxpayConfig {

    private Boolean useSandbox;

    private String appID;

    private String mchID;

    private String keyPath;

    private String certStreamPath;

    private Integer httpConnectTimeoutMs = 3000;

    private Integer httpReadTimeoutMs = 10000;

    private String notifyUrl = "";

    private String returnRrl = "";


    public Boolean getUseSandbox() {
        return useSandbox;
    }

    public void setUseSandbox(Boolean useSandbox) {
        this.useSandbox = useSandbox;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getMchID() {
        return mchID;
    }

    public void setMchID(String mchID) {
        this.mchID = mchID;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getCertStreamPath() {
        return certStreamPath;
    }

    public void setCertStreamPath(String certStreamPath) {
        this.certStreamPath = certStreamPath;
    }

    public Integer getHttpConnectTimeoutMs() {
        return httpConnectTimeoutMs;
    }

    public void setHttpConnectTimeoutMs(Integer httpConnectTimeoutMs) {
        this.httpConnectTimeoutMs = httpConnectTimeoutMs;
    }

    public Integer getHttpReadTimeoutMs() {
        return httpReadTimeoutMs;
    }

    public void setHttpReadTimeoutMs(Integer httpReadTimeoutMs) {
        this.httpReadTimeoutMs = httpReadTimeoutMs;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnRrl() {
        return returnRrl;
    }

    public void setReturnRrl(String returnRrl) {
        this.returnRrl = returnRrl;
    }

}