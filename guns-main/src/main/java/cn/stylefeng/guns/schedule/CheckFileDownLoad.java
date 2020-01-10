package cn.stylefeng.guns.schedule;

import cn.stylefeng.guns.controller.FtpOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CheckFileDownLoad {

    @Autowired
    private FtpOperation ftpOperation;

    @Scheduled(fixedRate = 1000)
    public void downWxFile(){
        log.info("下载微信对账文件！");
        ftpOperation.downloadFile2("/", "G_ZBCC_POST_[20191015]_W.txt", "C:/");
    }
}
