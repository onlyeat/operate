package cn.stylefeng.guns.service;

import cn.stylefeng.guns.BaseTest;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/26/17:05
 * @Description:
 */
@Slf4j
public class BusinessDetailServiceTest extends BaseTest {

	@Autowired
	private BusinessDetailService businessDetailService;

	@Test
	public void importFile() {
	}

	@Test
	public void listResult() {
		List<CheckDetail> checkDetails = businessDetailService.listResult("20191105");
		log.info(JSON.toJSONString(checkDetails));
	}
}