package com.huangpuguang.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.huangpuguang.pay.alipay.param.req.AppTradePayReq;
import com.huangpuguang.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能描述 </p>
 *
 * @author Procon
 * @since 2021/7/23
 */
@Slf4j
@Service
public class AliPayServiceImpl implements PayService {

    @Autowired
    private AlipayClient alipayClient;

    @Override
    public AlipayTradeAppPayResponse appOrderPay(AppTradePayReq appTradePayReq) {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        appTradePayReq.setTotalAmount("0.01");
        appTradePayReq.setOutTradeNo("2002213131");
        request.setBizContent("{" +
                "\"timeout_express\":\"90m\"," +
                "\"total_amount\":\"9.00\"," +
                "\"seller_id\":\"2088102147948060\"," +
                "\"product_code\":\"QUICK_MSECURITY_PAY\"," +
                "\"body\":\"Iphone6 16G\"," +
                "\"subject\":\"大乐透\"," +
                "\"out_trade_no\":\"70501111111S001111119\"," +
                "\"time_expire\":\"2016-12-31 10:05:00\"," +
                "\"goods_type\":\"0\"," +
                "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
                "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "\"royalty_info\":{" +
                "\"royalty_type\":\"ROYALTY\"," +
                "        \"royalty_detail_infos\":[{" +
                "          \"serial_no\":1," +
                "\"trans_in_type\":\"userId\"," +
                "\"batch_no\":\"123\"," +
                "\"out_relation_id\":\"20131124001\"," +
                "\"trans_out_type\":\"userId\"," +
                "\"trans_out\":\"2088101126765726\"," +
                "\"trans_in\":\"2088101126708402\"," +
                "\"amount\":0.1," +
                "\"desc\":\"分账测试1\"," +
                "\"amount_percentage\":\"100\"" +
                "          }]" +
                "    }," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"2088511833207846\"," +
                "\"hb_fq_num\":\"3\"," +
                "\"hb_fq_seller_percent\":\"100\"," +
                "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                "\"card_type\":\"S0JP0000\"," +
                "\"specified_seller_name\":\"XXX的跨境小铺\"" +
                "    }," +
                "\"sub_merchant\":{" +
                "\"merchant_id\":\"2088000603999128\"," +
                "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                "    }," +
                "\"merchant_order_no\":\"20161008001\"," +
                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "\"store_id\":\"NJ_001\"," +
                "\"specified_channel\":\"pcredit\"," +
                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                "      \"goods_detail\":[{" +
                "        \"goods_id\":\"apple-01\"," +
                "\"alipay_goods_id\":\"20010001\"," +
                "\"goods_name\":\"ipad\"," +
                "\"quantity\":1," +
                "\"price\":2000," +
                "\"goods_category\":\"34543238\"," +
                "\"categories_tree\":\"124868003|126232002|126252004\"," +
                "\"body\":\"特价手机\"," +
                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                "        }]," +
                "\"settle_info\":{" +
                "        \"settle_detail_infos\":[{" +
                "          \"trans_in_type\":\"cardAliasNo\"," +
                "\"trans_in\":\"A0001\"," +
                "\"summary_dimension\":\"A0001\"," +
                "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                "\"amount\":0.1" +
                "          }]," +
                "\"settle_period_time\":\"7d\"" +
                "    }," +
                "\"invoice_info\":{" +
                "\"key_info\":{" +
                "\"is_support_invoice\":true," +
                "\"invoice_merchant_name\":\"ABC|003\"," +
                "\"tax_num\":\"1464888883494\"" +
                "      }," +
                "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
                "    }," +
                "\"ext_user_info\":{" +
                "\"name\":\"李明\"," +
                "\"mobile\":\"16587658765\"," +
                "\"cert_type\":\"IDENTITY_CARD\"," +
                "\"cert_no\":\"362334768769238881\"," +
                "\"min_age\":\"18\"," +
                "\"fix_buyer\":\"F\"," +
                "\"need_check_info\":\"F\"" +
                "    }," +
                "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
                "\"agreement_sign_params\":{" +
                "\"personal_product_code\":\"CYCLE_PAY_AUTH_P\"," +
                "\"sign_scene\":\"INDUSTRY|DIGITAL_MEDIA\"," +
                "\"external_agreement_no\":\"test20190701\"," +
                "\"external_logon_id\":\"13852852877\"," +
                "\"access_params\":{" +
                "\"channel\":\"ALIPAYAPP\"" +
                "      }," +
                "\"sub_merchant\":{" +
                "\"sub_merchant_id\":\"2088123412341234\"," +
                "\"sub_merchant_name\":\"滴滴出行\"," +
                "\"sub_merchant_service_name\":\"滴滴出行免密支付\"," +
                "\"sub_merchant_service_description\":\"免密付车费，单次最高500\"" +
                "      }," +
                "\"period_rule_params\":{" +
                "\"period_type\":\"DAY\"," +
                "\"period\":3," +
                "\"execute_time\":\"2019-01-23\"," +
                "\"single_amount\":10.99," +
                "\"total_amount\":600," +
                "\"total_payments\":12" +
                "      }," +
                "\"allow_huazhi_degrade\":false," +
                "\"sign_notify_url\":\"http://www.merchant.com/receiveSignNotify\"" +
                "    }" +
                "  }");
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
           log.error("调用app下单接口异常！",e);
        }
        if(ObjectUtil.isNotNull(response) && response.isSuccess()){
            log.info("调用app下单成功：" +JSON.toJSONString(response));
        } else {
            log.info("调用app下单失败：" +JSON.toJSONString(response));
        }
        return response;
    }

}
