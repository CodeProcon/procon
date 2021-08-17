package com.huangpuguang.pay.alipay.param.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>app下单支付请求参数 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppTradePayReq {

    @JsonProperty(value = "total_amount")
    @ApiModelProperty(value = "订单总金额(单位为元，精确到小数点后两位，取值范围：[0.01,100000000] )",required = true)
    private String totalAmount;

    @ApiModelProperty(value = "订单标题",required = true)
    private String subject;

    @JsonProperty(value = "out_trade_no")
    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;
}
