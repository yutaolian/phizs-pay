/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.constants;

/**
 * @author lianyutao
 * @常量
 * @date 2018/1/17 13:57
 * @since 2.1.0
 */
public class PayConstants {

    /**
     * 支付平台编码-支付宝
     */

    public final static String pay_platform_code_alipay = "alipay";

    /**
     * 支付平台编码-微信支付
     */

    public final static String pay_platform_code_wxpay = "wxpay";


    /**
     * 支付平台-微信-支付类型-原生支付(扫码支付)
     */

    public final static String pay_platform_wxpay_sub_type_native = "NATIVE";

    /**
     * 支付平台-微信-支付类型-公众号支付
     */

    public final static String pay_platform_wxpay_sub_type_jsapi = "JSAPI";

    /**
     * 支付平台-微信-支付类型-app支付
     */

    public final static String pay_platform_wxpay_sub_type_app = "APP";

    /**
     * 支付平台-支付宝-支付类型-电脑网站
     */

    public final static String pay_platform_alipay_sub_type_webpc = "WEBPC";

    /**
     * 支付平台-支付宝-支付类型-手机网页支付
     */

    public final static String pay_platform_alipay_sub_type_wap = "WAP";

    /**
     * 支付平台-支付宝-支付类型-app
     */

    public final static String pay_platform_alipay_sub_type_app = "app";


    /**
     *    交易状态：
     *  WAIT_BUYER_PAY（交易创建，等待买家付款）、
     *  TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     *  TRADE_SUCCESS（交易支付成功）、
     *  TRADE_FINISHED（交易结束，不可退款）
     */

    public final static String pay_alipay_trade_status_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    public final static String pay_alipay_trade_status_TRADE_CLOSED = "TRADE_CLOSED";

    public final static String pay_alipay_trade_status_TRADE_SUCCESS= "TRADE_SUCCESS";

    public final static String pay_alipay_trade_status_TRADE_FINISHED= "TRADE_FINISHED";


    public final static String pay_alipay_trade_status_EXCEPTION = "QUERY_EXCEPTION";



}