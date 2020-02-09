package cn.stylefeng.guns.schedule;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.controller.FtpOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CheckFileDownLoad {

    @Autowired
    private FtpOperation ftpOperation;

    @Scheduled(fixedRate = 1000)
    public void downWxFile(){
        String day = DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
        log.info("下载微信对账文件！日期{}", day);
        ftpOperation.downloadFile2("/", "G_ZBCC_POST_[20191015]_W.txt", "C:/");
    }
}
