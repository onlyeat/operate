package cn.stylefeng.guns.sys.modular.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中百微信渠道对账
 */
@RestController("weixin2")
public class WeixinChannelCheck {

    /**
     * 手工操作对账 获取对账结果
     * @return
     */
    @PostMapping("manual/check2")
    public String manualCheck(){
        return "success";
    }
}
