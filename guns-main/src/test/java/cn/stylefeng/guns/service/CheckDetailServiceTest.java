package cn.stylefeng.guns.service;

import cn.stylefeng.guns.BaseTest;
import cn.stylefeng.guns.modular.entity.CheckDetail;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/29/20:07
 * @Description:
 */
@Slf4j
public class CheckDetailServiceTest extends BaseTest {

	@Autowired
	private CheckDetailService checkDetailService;


	@Test
	public void insertBatch() {
	}

	@Test
	public void queryList() {
		CheckDetail checkDetail = new CheckDetail();
		checkDetailService.queryList(checkDetail);
	}

	@Test
	public void getSumTradeAmountTest() {
		CheckDetail checkDetail = new CheckDetail();
		log.info(JSON.toJSONString(checkDetailService.getSumTradeAmount(checkDetail)));
	}
}