package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.enums.TradeChannelEnum;
import cn.stylefeng.guns.dao.BusinessDetailDao;
import cn.stylefeng.guns.dao.FileResultDao;
import cn.stylefeng.guns.modular.entity.BusinessDetail;
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
public class BusinessDetailService {

	@Autowired
	private FileResultDao fileResultDao;

	@Autowired
	private BusinessDetailDao businessDetailDao;

	/**
	 * 富基业务数据入库
	 * @param fileName
	 * @return
	 */
	public Boolean importFile(String fileName) {
		File file = new File(fileName);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int line = 1;
		String temp = null;
		List<BusinessDetail> businessDetailList = Lists.newArrayList();
		try {
			while ((temp = bufferedReader.readLine()) != null) {
				if (1 == line) {
					//富基汇总 第一行 62|38400
					String[] firstRow = temp.split("|");
					FileResult fileResult = new FileResult();
					fileResult.setTradeNumber(firstRow[0]);
					fileResult.setTradeAmount(new BigDecimal(firstRow[1]));
					fileResult.setFileDate(new Timestamp(new Date().getTime()));
					fileResult.setChannel(TradeChannelEnum.WEI_XIN.getDesc());
					fileResultDao.insert(fileResult);
				}
				//898420154119049||20191105|1105100940||1911011000000467|ZFB|200|||ZBQB|||||||||
				String[] detailRow = temp.split(",");
				BusinessDetail businessDetail = new BusinessDetail(detailRow);
				businessDetailList.add(businessDetail);
				line++;
			}
			if (CollectionUtils.isEmpty(businessDetailList)) {
				businessDetailDao.insertBatch(businessDetailList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
