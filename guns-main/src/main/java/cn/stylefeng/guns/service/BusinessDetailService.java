package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.enums.CheckStatusEnum;
import cn.stylefeng.guns.base.enums.TradeChannelEnum;
import cn.stylefeng.guns.dao.BankDetailDao;
import cn.stylefeng.guns.dao.BusinessDetailDao;
import cn.stylefeng.guns.dao.FileResultDao;
import cn.stylefeng.guns.modular.entity.BankDetail;
import cn.stylefeng.guns.modular.entity.BusinessDetail;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import cn.stylefeng.guns.modular.entity.FileResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusinessDetailService {

	@Autowired
	private FileResultDao fileResultDao;

	@Autowired
	private BusinessDetailDao businessDetailDao;

	@Autowired
	private BankDetailDao bankDetailDao;

	/**
	 * 富基业务数据入库
	 *
	 * @param fileName
	 * @return
	 */
	public Boolean importFile(String date, String fileName) {
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
					String[] firstRow = temp.split("\\|");
					FileResult fileResult = new FileResult();
					fileResult.setFileBatchId(String.format("%s%s", date, TradeChannelEnum.FU_JI.getDesc()));
					fileResult.setTradeNumber(firstRow[0]);
					fileResult.setTradeAmount(new BigDecimal(firstRow[1]));
					fileResult.setFileDate(new Timestamp(new Date().getTime()));
					fileResult.setChannel(TradeChannelEnum.FU_JI.getDesc());
					fileResultDao.insert(fileResult);
					line++;
					continue;
				}
				//898420154119049||20191105|1105100940||1911011000000467|ZFB|200|||ZBQB|||||||||
				String[] detailRow = temp.split("\\|");
				log.info("富基明细第{}行", line);
				BusinessDetail businessDetail = new BusinessDetail(detailRow);
				businessDetailList.add(businessDetail);
				line++;
			}
			if (!CollectionUtils.isEmpty(businessDetailList)) {
				businessDetailDao.insertBatch(businessDetailList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 对账
	 *
	 * @param date
	 * @return
	 */
	public List<CheckDetail> listResult(String date) {
		//获取对账量表的记录
		List<BusinessDetail> businessList = businessDetailDao.listBusinessDetail(date);
		//银商 银联为主表
		List<BankDetail> bankDetailList = bankDetailDao.listBankDetail(date);
		if (CollectionUtils.isEmpty(businessList) || bankDetailList.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		Map<String, BusinessDetail> businessMap = businessList.stream().
				collect(Collectors.toMap(BusinessDetail::getIndexNo, e -> e, (key1, key2) -> key2));
		List<CheckDetail> checkList = Lists.newArrayList();
		List<BankDetail> removeBankList = Lists.newArrayList();
		List<BusinessDetail> removeBusinessList = Lists.newArrayList();
		bankDetailList.forEach(bankDetail -> {
			//检索参考号 即订单号
			String indexNo = bankDetail.getIndexNo();
			BusinessDetail businessDetail = businessMap.get(indexNo);
			//BeanUtils.copyProperties();
			CheckDetail checkDetail = new CheckDetail();
			//银商有 中百有
			if (!ObjectUtils.isEmpty(businessDetail)) {
				BeanUtils.copyProperties(bankDetail, checkDetail);
				//金额相等
				if (0 == businessDetail.getTradeAmount().compareTo(bankDetail.getTradeAmount())) {
					checkDetail.setCheckStatus(CheckStatusEnum.OK.getCode());
				} else {
					checkDetail.setCheckStatus(CheckStatusEnum.WRONG_AMOUNT.getCode());
				}
				removeBankList.add(bankDetail);
				removeBusinessList.add(businessDetail);
			}
			//银商有 中百无
			if (ObjectUtils.isEmpty(businessDetail)) {
				checkDetail.setMerchantNo(bankDetail.getMerchantNo());
				checkDetail.setClearDate(bankDetail.getClearDate());
				checkDetail.setTradeDate(bankDetail.getTradeDate());
				checkDetail.setIndexNo(bankDetail.getIndexNo());
				checkDetail.setTradeAmount(bankDetail.getTradeAmount());
				checkDetail.setCheckStatus(CheckStatusEnum.ZHONG_BAI_LESS.getCode());
			}
			checkList.add(checkDetail);
		});
		bankDetailList.removeAll(removeBankList);
		businessList.removeAll(removeBusinessList);
		//银商剩下
		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(bankDetailList)) {
			bankDetailList.forEach(bankDetailLeave ->{
				CheckDetail checkDetail = new CheckDetail();
				checkDetail.setMerchantNo(bankDetailLeave.getMerchantNo());
				checkDetail.setClearDate(bankDetailLeave.getClearDate());
				checkDetail.setTradeDate(bankDetailLeave.getTradeDate());
				checkDetail.setIndexNo(bankDetailLeave.getIndexNo());
				checkDetail.setTradeAmount(bankDetailLeave.getTradeAmount());
				checkDetail.setCheckStatus(CheckStatusEnum.BANK_MORE_LEAVE.getCode());
				checkList.add(checkDetail);
			} );
		}
        //中百剩下的
		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(businessList)) {
			businessList.forEach(bankDetailLeave -> {
				CheckDetail checkDetail = new CheckDetail();
				BeanUtils.copyProperties(bankDetailLeave, checkDetail);
				checkDetail.setCheckStatus(CheckStatusEnum.ZHONG_BAI_MORE.getCode());
				checkList.add(checkDetail);
			});
		}
		return checkList;
	}
}
