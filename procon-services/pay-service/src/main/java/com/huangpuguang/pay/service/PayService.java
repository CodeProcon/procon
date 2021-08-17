package com.huangpuguang.pay.service;

import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.huangpuguang.pay.alipay.param.req.AppTradePayReq;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */
public interface PayService {
    AlipayTradeAppPayResponse appOrderPay(AppTradePayReq appTradePayReq);
}
