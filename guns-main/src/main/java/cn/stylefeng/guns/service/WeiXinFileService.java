package cn.stylefeng.guns.service;

import cn.stylefeng.guns.base.enums.TradeChannelEnum;
import cn.stylefeng.guns.dao.FileResultDao;
import cn.stylefeng.guns.modular.entity.FileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class WeiXinFileService {

	@Autowired
	private FileResultDao fileResultDao;

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
        try {
            while ((temp = bufferedReader.readLine()) != null) {
                if (1 == line) {
                    //微信汇总 第一行 36,20100.00,19979.40,0,0.00,0.00
                    String[] split = temp.split(",");
                    FileResult fileResult = new FileResult(split[0], new BigDecimal(split[1]), new BigDecimal(split[2]));
					fileResult.setFileDate(new Timestamp(new Date().getTime()));
                    fileResult.setChannel(TradeChannelEnum.WEI_XIN.getDesc());
                    fileResultDao.insert(fileResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
