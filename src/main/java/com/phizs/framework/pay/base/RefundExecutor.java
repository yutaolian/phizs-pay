package com.phizs.framework.pay.base;


import com.phizs.framework.pay.bean.OrderRefund;

import java.util.Map;

/**
 *退款接口
 */
public interface RefundExecutor {


    /***
     * 申请退款
     * @param refund
     * @return
     * @throws Exception
     */
    Map<String, Object> refund(OrderRefund refund) throws Exception;

    /***
     * 申请退款
     * @param refund
     * @return
     * @throws Exception
     */
    Map<String, Object> queryRefund(OrderRefund refund) throws Exception;
}
