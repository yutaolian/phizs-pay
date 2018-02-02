/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.wxpay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.phizs.framework.pay.base.PayExecutor;
import com.phizs.framework.pay.base.RefundExecutor;
import com.phizs.framework.pay.bean.OrderPayment;
import com.phizs.framework.pay.bean.OrderRefund;
import com.phizs.framework.pay.config.WxpayConfig;
import com.phizs.framework.pay.util.FileUtil;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianyutao
 * @微信支付
 * @date 2018/1/18 16:23
 * @since 2.1.0
 */
public class WxpayExecutor implements PayExecutor, RefundExecutor {

	// 支付线程锁
	@SuppressWarnings("unused")
	private static Object pay_lock = new Object();

	private WxpayConfig wxpayConfig;

	public WxpayConfig getWxpayConfig() {
		return wxpayConfig;
	}

	public void setWxpayConfig(WxpayConfig wxpayConfig) {
		this.wxpayConfig = wxpayConfig;
	}

	@Override
	public Map<String, Object> pay(OrderPayment payment) throws Exception {
		synchronized (pay_lock) {
			WXPay wxPay = getClient();
			Map<String, Object> result = new HashMap<>();
			Map<String, String> param = new HashMap<>();

			// 商品描述 body 是 String(128) 腾讯充值中心-QQ会员充值
			// 商户订单号 out_trade_no 是 String(32) 20150806125346
			// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
			// 标价金额 total_fee 是 Int 88 订单总金额，单位为分，详见支付金额
			// 终端IP spbill_create_ip 是 String(16) 123.12.12.123
			// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			// 通知地址 notify_url 是 String(256) http://www.weixin.qq.com/wxpay/pay.php
			// 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
			// 交易类型 trade_type 是 String(16) JSAPI 取值如下：JSAPI，NATIVE，APP等，说明详见参数规定

			param.put("body", payment.getBody());
			param.put("out_trade_no", payment.getOutTradeNo());
			// TODO 测试时设成1分 上线要修改这里
			param.put("total_fee", String.valueOf(payment.getTotalAmount().multiply(BigDecimal.valueOf(100)).intValue()));
			// param.put("total_fee", "100");
			param.put("spbill_create_ip", payment.getSpbillCreateIp());
			param.put("notify_url", wxpayConfig.getNotifyUrl());
			param.put("trade_type", payment.getPlatformSubType());
			param.put("nonce_str", System.currentTimeMillis() + "");

			//TODO 移动端指端支付是需要传入openid
			// if(PayConstants.pay_platform_wxpay_sub_type_jsapi.equals(payment.getPlatformSubType())){
			// if(!ObjectUtils.isEmpty(payParam.getPlatformSubParam().get("openid"))){
			// param.put("openid",
			// payParam.getPlatformSubParam().get("openid").toString());
			// }
			// }
            //TODO  这个需要在微信账号里开通权限
			// if (payment.isMWEB()){
			// param.put("scene_info","{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"http://www.91haoke.com\",\"wap_name\":\"91好课移动版\"}}");
			// }

			Map<String, String> response = wxPay.unifiedOrder(param);
			// 检查接口调用是否成功
			// isWxpaySuccess(response);
			// response.put("key",key);
			result.putAll(response);
			return result;
		}
	}

	@Override
	public Map<String, Object> refund(OrderRefund refund) throws Exception {
		return null;
	}

	@Override
	public String queryOrderPayStatus(String out_trade_no) throws Exception {
		return null;
	}

	@Override
	public Map<String, Object> queryRefund(OrderRefund refund) throws Exception {
		return null;
	}

	@Override
	public Map<String, Object> queryOrderPayInfo(String out_trade_no) throws Exception {
		return null;
	}

	@Override
	public boolean cancelPayOrder(String orderNo) throws Exception {
		return false;
	}

	@Override
	public boolean checkNotify(Map<String, String> map) {
		return false;
	}

	@Override
	public boolean checkReturn(Map<String, String> map) {
		return false;
	}

	public WXPay getClient() {
		WXPayConfig config = new WXPayConfig() {
			@Override
			public String getAppID() {
				return wxpayConfig.getAppID();
			}

			@Override
			public String getMchID() {
				return wxpayConfig.getMchID();
			}

			@Override
			public String getKey() {
				return FileUtil.getText(wxpayConfig.getKeyPath());
			}

			@Override
			public InputStream getCertStream() {
				return FileUtil.getInputStream(wxpayConfig.getCertStreamPath());
			}

			@Override
			public int getHttpConnectTimeoutMs() {
				return wxpayConfig.getHttpConnectTimeoutMs();
			}

			@Override
			public int getHttpReadTimeoutMs() {
				return wxpayConfig.getHttpReadTimeoutMs();
			}
		};
		WXPay wxPay = new WXPay(config);
		return wxPay;
	}
}