package cn.stylefeng.guns.schedule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.controller.FtpOperation;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import cn.stylefeng.guns.service.BankDetailService;
import cn.stylefeng.guns.service.BusinessDetailService;
import cn.stylefeng.guns.service.CheckDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class CheckFileDownLoad {

	@Autowired
	private FtpOperation ftpOperation;

	@Autowired
	private BankDetailService bankDetailService;

	@Autowired
	private BusinessDetailService businessDetailService;

	@Autowired
	private CheckDetailService checkDetailService;

	//@Scheduled(fixedRate = 1000)
	public void downWxFile() {
		String day = DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
		log.info("下载微信对账文件！日期{}", day);
		//1- ftp 获取对账文件
		//ftpOperation.downloadFile2("G:/javaTest/", "G_ZBCC_POST_[20191105]_W.txt", "C:/");
		//TODO 云闪付文件名称 IND19110532ACOMA
		//2- 入库
		Boolean wBoolean = bankDetailService.importFile(day,"G:/javaTest/G_ZBCC_POST_[20191105]_W.txt");
		log.info("微信对账文件入库结果{}", wBoolean.booleanValue() == true ? "成功" : "失败");
		Boolean bBoolean = businessDetailService.importFile(day,"G:/javaTest/ZBJT_ONLINE_[20191105].txt");
		log.info("富基对账文件入库结果{}", bBoolean.booleanValue() == true ? "成功" : "失败");
		//3- 对比对账文件入 结果明细表
		List<CheckDetail> checkDetails = businessDetailService.listResult(day);
		//4- 结果明细入库
		checkDetailService.insertBatch(checkDetails);
	}
}
