package com.phizs.framework.pay.base;



import com.phizs.framework.pay.bean.OrderPayment;

import java.util.Map;

/**
 * 支付接口
 */
public interface PayExecutor {


    /**
     * 查询订单支付状态
     *
     * @param out_trade_no 商户订单号
     * @return
     * @throws Exception
     */
    String queryOrderPayStatus(String out_trade_no) throws Exception;

    /**
     * 查询订单支付信息
     *
     * @param out_trade_no 商户订单号
     * @return
     * @throws Exception
     */
    Map<String,Object> queryOrderPayInfo(String out_trade_no) throws Exception;


    /**
     * 取消未付款的订单
     * @param orderNo
     * @return
     * @throws Exception
     */
    boolean cancelPayOrder(String orderNo) throws Exception;


    /**
     * 校验notify数据
     * @param map
     * @return
     */
    boolean checkNotify(Map<String, String> map);


    /**
     * 校验return数据
     * @param map
     * @return
     */
    boolean checkReturn(Map<String, String> map);


    /***
     * 去支付
     * @param payment
     * @return
     * @throws Exception
     */
    Map<String, Object> pay(OrderPayment payment) throws Exception;
}
