package com.huangpuguang.sms.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * <p>短信请求参数 </p>
 *
 * @author Procon
 * @since 2021/7/15
 */
@Data
@ToString
@Builder
@ApiModel("短信发送参数")
public class SmsParam {
    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("短信内容")
    private String content;

    @ApiModelProperty("签名名称")
    private String signName;
}
