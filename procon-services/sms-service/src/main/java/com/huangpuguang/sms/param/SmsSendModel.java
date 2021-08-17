package com.huangpuguang.sms.param;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * <p>短信发送参数 </p>
 *
 * @author Procon
 * @since 2021/7/16
 */
@Data
@ToString
@RefreshScope
@ApiModel("短信发送model")
public class SmsSendModel {

    @ApiModelProperty("短信模板编码")
    private String templateCode;
    @ApiModelProperty("短信签名")
    private String signName;
    @ApiModelProperty("接收人手机号")
    private String phone;
    @ApiModelProperty("短信模板参数")
    private JSONObject templateParam;
}
