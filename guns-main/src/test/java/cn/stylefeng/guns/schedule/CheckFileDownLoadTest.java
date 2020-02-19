package cn.stylefeng.guns.schedule;

import cn.stylefeng.guns.BaseTest;
import cn.stylefeng.guns.controller.FtpOperation;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import cn.stylefeng.guns.service.BankDetailService;
import cn.stylefeng.guns.service.BusinessDetailService;
import cn.stylefeng.guns.service.CheckDetailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/18/20:49
 * @Description:
 */
@Slf4j

public class CheckFileDownLoadTest extends BaseTest {

	@Autowired
	private FtpOperation ftpOperation;

	@Autowired
	private BankDetailService bankDetailService;

	@Autowired
	private BusinessDetailService businessDetailService;

	@Autowired
	private CheckDetailService checkDetailService;

	@Test
	public void testDownWxFile() {
		String day = "20191105";
		log.info("下载微信对账文件！日期{}", day);
		//1- ftp 获取对账文件
		//ftpOperation.downloadFile2("G:/javaTest/", "G_ZBCC_POST_[20191105]_W.txt", "C:/");
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

	@Test
	public void testLineSplit(){
		String tmp = "898420154119049||20191105|1105100940||1911011000000467|ZFB|200|||ZBQB|||||||||";
//		String tmp = "898420154119049|20191105|1105100940||1911011000000467|ZFB|200|ZBQB";
		String[] split = tmp.split("\\|");
		for (String s : split) {
			log.info(s);
		}
	}
}