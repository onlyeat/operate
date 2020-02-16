package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.enums.TradeChannelEnum;
import cn.stylefeng.guns.dao.BankDetailDao;
import cn.stylefeng.guns.dao.FileResultDao;
import cn.stylefeng.guns.modular.entity.BankDetail;
import cn.stylefeng.guns.modular.entity.FileResult;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BankDetailService {

	@Autowired
	private FileResultDao fileResultDao;

	@Autowired
	private BankDetailDao bankDetailDao;

	/**
	 * 微信对账文件导入
	 *
	 * @param pathName
	 * @param fileName
	 * @return
	 */
	public Boolean importFile(String pathName, String fileName) {
		File file = new File(String.format("%s%s", pathName, fileName));
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int line = 1;
		String temp = null;
		List<BankDetail> bankDetailsList = Lists.newArrayList();
		try {
			while ((temp = bufferedReader.readLine()) != null) {
				if (1 == line) {
					//微信汇总 第一行 36,20100.00,19979.40,0,0.00,0.00
					String[] firstRow = temp.split(",");
					FileResult fileResult = new FileResult(firstRow[0], new BigDecimal(firstRow[1]), new BigDecimal(firstRow[2]));
					fileResult.setFileDate(new Timestamp(new Date().getTime()));
					fileResult.setChannel(TradeChannelEnum.WEI_XIN.getDesc());
					fileResultDao.insert(fileResult);
				}
				//1558678941,,20191105,1105205836,,,,100.00,0.60,99.40,1105205836,,S22,,,4200000424201911054718388463,PURH1911011000000526,0,,,,,,
				String[] detailRow = temp.split(",");
				BankDetail bankDetail = new BankDetail(detailRow);
				bankDetailsList.add(bankDetail);
				line++;
			}
			if (CollectionUtils.isEmpty(bankDetailsList)) {
				bankDetailDao.insertBatch(bankDetailsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
