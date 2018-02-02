
package com.phizs.framework.pay.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.phizs.framework.pay.base.PayExecutor;
import com.phizs.framework.pay.base.RefundExecutor;
import com.phizs.framework.pay.bean.OrderPayment;
import com.phizs.framework.pay.bean.OrderRefund;
import com.phizs.framework.pay.config.AlipayConfig;
import com.phizs.framework.pay.constants.PayConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianyutao
 * @支付宝支付
 * @date 2018/1/17 13:32
 * @since 2.1.0
 */

public class AlipayExecutor implements PayExecutor, RefundExecutor {

	// 支付线程锁
	private static Object pay_lock = new Object();

	private static final String encoding = "utf-8";

	private AlipayConfig alipayConfig;

	public AlipayConfig getAlipayConfig() {
		return alipayConfig;
	}

	public void setAlipayConfig(AlipayConfig alipayConfig) {
		this.alipayConfig = alipayConfig;
	}

	public AlipayClient getClient() {
		String merchant_private_key = getText(alipayConfig.getMerchant_private_key_path());
		String alipay_public_key = getText(alipayConfig.getAlipay_public_key_path());
		AlipayClient client = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getApp_id(),
				merchant_private_key, alipayConfig.getFormat(), alipayConfig.getCharset(), alipay_public_key,
				alipayConfig.getSign_type());
		return client;
	}

	/**
	 * 统一下单，返回支付宝支付页面
	 *
	 * @param payment
	 * @return 会返回调起的html代码
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> pay(OrderPayment payment) throws Exception {
		synchronized (pay_lock) {
			AlipayClient alipayClient = getClient();
			// 返回结果
			Map<String, Object> result = new HashMap<>();
			String platformSubType = payment.getPlatformSubType();
			//
			result.put("platformCode", PayConstants.pay_platform_code_alipay);
			result.put("platformSubType", platformSubType);
			// 商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = payment.getOutTradeNo();
			// 付款金额，必填
			String total_amount = payment.getTotalAmount().toPlainString();
			// 订单名称，必填
			String subject = payment.getSubject();
			// 商品描述，可空
			String body = payment.getBody();
			// 商品描述，可空
			String timeout_express = payment.getTimeoutExpress();

			JSONObject content = new JSONObject();
			content.put("out_trade_no", out_trade_no);
			content.put("total_amount", total_amount);
			content.put("subject", subject);
			content.put("body", body);
			content.put("timeout_express", timeout_express);
			content.put("product_code", "FAST_INSTANT_TRADE_PAY");
			// 请求结果
			if (PayConstants.pay_platform_alipay_sub_type_wap.equals(platformSubType)) {
				// 移动端支付
				AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
				alipayRequest.setReturnUrl(alipayConfig.getM_return_url());
				alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
				alipayRequest.setBizContent(content.toJSONString());
				AlipayTradeWapPayResponse wapPayResponse = alipayClient.pageExecute(alipayRequest);
				result.put("form", wapPayResponse.getBody());
				// 请求结果
			} else if (PayConstants.pay_platform_alipay_sub_type_webpc.equals(platformSubType)) {
				AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
				alipayRequest.setReturnUrl(alipayConfig.getPc_return_url());
				alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
				alipayRequest.setBizContent(content.toJSONString());
				AlipayTradePagePayResponse pagePayResponse = alipayClient.pageExecute(alipayRequest);
				result.put("form", pagePayResponse.getBody());
			}
			return result;
		}
	}

	/**
	 * 查询订单支付状态
	 *
	 * @param out_trade_no
	 * @return
	 * @throws Exception
	 */
	@Override
	public String queryOrderPayStatus(String out_trade_no) throws Exception {
		synchronized (pay_lock) {
			try {
				AlipayClient alipayClient = getClient();
				// 设置请求参数
				AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
				// 商户订单号，商户网站订单系统中唯一订单号
				JSONObject content = new JSONObject();
				content.put("out_trade_no", out_trade_no);
				alipayRequest.setBizContent(content.toJSONString());
				AlipayTradeQueryResponse response = null;
				response = alipayClient.execute(alipayRequest);

				return response.getTradeStatus();
			} catch (AlipayApiException e) {
				return PayConstants.pay_alipay_trade_status_EXCEPTION;
			}

		}
	}

	@Override
	public Map<String, Object> queryOrderPayInfo(String out_trade_no) throws Exception {
		synchronized (pay_lock) {
			Map<String, Object> result = new HashMap<>();
			try {
				AlipayClient alipayClient = getClient();
				// 设置请求参数
				AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
				// 商户订单号，商户网站订单系统中唯一订单号
				JSONObject content = new JSONObject();
				content.put("out_trade_no", out_trade_no);
				alipayRequest.setBizContent(content.toJSONString());
				AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
				String body = response.getBody();
				Map<String, Object> maps = JSON.parseObject(body);
				result.put("alipay_trade_query_response", maps.get("alipay_trade_query_response"));
			} catch (AlipayApiException e) {
				result = null;
			}

			return result;
		}
	}

	/**
	 * 取消已下的订单
	 *
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean cancelPayOrder(String orderNo) throws Exception {
		synchronized (pay_lock) {

		}
		return false;
	}

	/**
	 * notify校验
	 *
	 * @param map
	 * @return
	 */
	@Override
	public boolean checkNotify(Map<String, String> map) {

		synchronized (pay_lock) {
			return false;
		}
	}

	/**
	 * return 校验
	 *
	 * @param map
	 * @return
	 */
	@Override
	public boolean checkReturn(Map<String, String> map) {

		synchronized (pay_lock) {
			return false;
		}
	}

	/**
	 * 退款
	 *
	 * @param refund
	 * @return
	 * @throws Exception
	 */

	@Override
	public Map<String, Object> refund(OrderRefund refund) throws Exception {
		synchronized (pay_lock) {
			AlipayClient alipayClient = getClient();
			// 退款返回结果
			Map<String, Object> result = new HashMap<>();

			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			// 支付宝回调的编码，必填
			String out_trade_no = refund.getOutTradeNo();
			String refund_amount = refund.getRefundAmount().toPlainString();
			String refund_reason = refund.getRefundReason();
			String out_request_no = refund.getOutRequestNo();

			JSONObject content = new JSONObject();
			content.put("out_trade_no", out_trade_no);
			content.put("refund_amount", refund_amount);
			content.put("refund_reason", refund_reason);
			content.put("out_request_no", out_request_no);

			alipayRequest.setBizContent(content.toJSONString());
			AlipayTradeRefundResponse response = alipayClient.execute(alipayRequest);
			String body = response.getBody();
			Map<String, Object> maps = JSON.parseObject(body);
			result.put("alipay_trade_refund_response", maps.get("alipay_trade_refund_response"));
			return result;
		}
	}

	/**
	 * 查询退款
	 * 
	 * @param refund
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> queryRefund(OrderRefund refund) throws Exception {
		synchronized (pay_lock) {
			AlipayClient alipayClient = getClient();
			// 退款返回结果
			Map<String, Object> result = new HashMap<>();

			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			// 支付宝回调的编码，必填
			String out_trade_no = refund.getOutTradeNo();
			String refund_amount = refund.getRefundAmount().toPlainString();
			String refund_reason = refund.getRefundReason();
			String out_request_no = refund.getOutRequestNo();

			JSONObject content = new JSONObject();
			content.put("out_trade_no", out_trade_no);
			content.put("out_request_no", out_request_no);
			alipayRequest.setBizContent(content.toJSONString());
			AlipayTradeRefundResponse response = alipayClient.execute(alipayRequest);
			String body = response.getBody();
			Map<String, Object> maps = JSON.parseObject(body);
			result.put("alipay_trade_refund_response", maps.get("alipay_trade_refund_response"));
			return result;
		}
	}

	/**
	 * 获取字符串
	 *
	 * @param location
	 * @return
	 */
	private String getText(String location) {
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource resource = patternResolver.getResource(location);
		InputStream in = null;
		try {
			in = resource.getInputStream();
			return IOUtils.toString(in, encoding);
		} catch (IOException e) {
			return null;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

}