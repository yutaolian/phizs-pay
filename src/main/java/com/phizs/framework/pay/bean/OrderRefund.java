/*
 * Copyright (c) 2016,gaosiedu.com
 */
package com.phizs.framework.pay.bean;

import java.math.BigDecimal;

/**
 * @author lianyutao
 * @退款
 * @date 2018/1/18 15:46
 * @since 2.1.0
 */
public class OrderRefund {


    /**
     * 下单时的商户生成的订单编号
     */

    private String outTradeNo;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 商户生成的退款单号
     */
    private String outRequestNo;



    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }
}