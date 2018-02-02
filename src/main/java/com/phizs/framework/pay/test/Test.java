/*
 * Copyright (c) 2016,gaosiedu.com
 */
package com.phizs.framework.pay.test;


import com.phizs.framework.pay.alipay.AlipayExecutor;
import com.phizs.framework.pay.base.PayExecutor;
import com.phizs.framework.pay.bean.OrderPayment;
import com.phizs.framework.pay.bean.OrderRefund;
import com.phizs.framework.pay.wxpay.WxpayExecutor;

import java.math.BigDecimal;

/**
 * @author lianyutao
 * @test
 * @date 2018/1/18 19:13
 * @since 2.1.0
 */
public class Test {

	// TODO 此方法在移动端或pc端执行

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		try {
			PayExecutor payExecutor = new AlipayExecutor();
			PayExecutor wxpayExecutor = new WxpayExecutor();
			OrderPayment payment = new OrderPayment();
			payment.setBody("test body");
			// 过期时间2小时之后过期
			payment.setTimeoutExpress("2h");
			payment.setTotalAmount(new BigDecimal("0.01"));
			payment.setOutTradeNo("20180118234234");
			payment.setSubject("test subject");
			payment.setPlatformSubType("WAP");

			OrderRefund refund = new OrderRefund();

			refund.setOutTradeNo("354400746824");
			refund.setRefundAmount(new BigDecimal("0.01"));
			refund.setRefundReason("正常退款");
			refund.setOutRequestNo("7934927498723489");

			OrderPayment payment1 = new OrderPayment();

			payment1.setBody("test body");
			payment1.setTotalAmount(new BigDecimal("0.01"));
			payment1.setOutTradeNo("2018011823423434");
			payment1.setSubject("test subject");
			payment1.setPlatformSubType("NATIVE");
			payment1.setSpbillCreateIp("192.168.0.1");
			payment1.setNotifyUrl("http://91haoke.com");

			wxpayExecutor.pay(payment1);

			// return alipayExecutor.refund(refund);
			// return payExecutor.pay(payment);
			// return payExecutor.queryOrderPayInfo(tag);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}