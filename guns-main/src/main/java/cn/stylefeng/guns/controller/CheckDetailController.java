package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import cn.stylefeng.guns.service.CheckDetailService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/28/0:10
 * @Description:
 */
@RestController("/checkDetail")
@Slf4j
public class CheckDetailController {

	@Autowired
	private CheckDetailService checkDetailService;

	public static final String PREFIX = "/checkDetail/";

	@RequestMapping("")
	public String index() {
		return PREFIX + "checkDetail.html";
	}

	/**
	 * 对账结果数据
	 * @param checkDetail
	 * @return
	 */
	@RequestMapping("/queryList")
	public LayuiPageInfo queryList(CheckDetail checkDetail) {
		Page<Map<String, Object>> checkDetailList = checkDetailService.queryList(checkDetail);
		//  Page wrapped = new UserWrapper(merchants).wrap();
//        Page = merchantService.fillObject(merchants);
		return LayuiPageFactory.createPageInfo(checkDetailList);
	}

//	@RequestMapping("/addView")
//	public ModelAndView addView(ModelAndView mv){
//		mv.setViewName(PREFIX + "merchant_add.html");
//		return mv;
//	}
}
