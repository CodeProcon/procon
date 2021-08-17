package com.huangpuguang.pay.alipay.controller;

import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.huangpuguang.pay.alipay.param.req.AppTradePayReq;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PayService payService;

    @PostMapping("/appTradePay")
    public ResultModel<AlipayTradeAppPayResponse> appTradePay(@RequestBody AppTradePayReq appTradePayReq){

        return ResultModel.ok(payService.appOrderPay(appTradePayReq));

    }
}
