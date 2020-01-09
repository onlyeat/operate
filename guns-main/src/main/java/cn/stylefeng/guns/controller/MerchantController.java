package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.service.MerchantService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    public static final String PREFIX = "/merchant/";

    @RequestMapping("")
    public String index() {
        return PREFIX + "merchant.html";
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public LayuiPageInfo queryList(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "code", required = false) String code) {

        Page<Map<String, Object>> merchants = merchantService.listMerchants(name, code);
      //  Page wrapped = new UserWrapper(merchants).wrap();
//        Page = merchantService.fillObject(merchants);
        return LayuiPageFactory.createPageInfo(merchants);
    }

    @RequestMapping("/addView")
    public ModelAndView addView(ModelAndView mv){
        mv.setViewName(PREFIX + "merchant_add.html");
        return mv;
    }

}
