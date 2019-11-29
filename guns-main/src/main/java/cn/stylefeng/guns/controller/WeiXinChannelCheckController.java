package cn.stylefeng.guns.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中百微信渠道对账
 */
@RestController
@RequestMapping("/weixin")
public class WeiXinChannelCheckController extends BaseController {

    /**
     * 手工操作对账 获取对账结果
     * @return
     */
    @GetMapping("manual/check")
    public String manualCheck(){
        return "success";
    }
}
