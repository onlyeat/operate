package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.BaseTest;
import cn.stylefeng.guns.modular.entity.BankDetail;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.swing.BakedArrayList;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/18/21:43
 * @Description:
 */
public class BankDetailDaoTest extends BaseTest {

	@Autowired
	private BankDetailDao bankDetailDao;

	@Test
	public void getById() {
	}

	@Test
	public void query() {
	}

	@Test
	public void insert() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void insertBatch() {
		BankDetail bankDetail1 = new BankDetail();
		BankDetail bankDetail2= new BankDetail();

		bankDetail1.setFileBatchId("111");
		bankDetail2.setFileBatchId("222");
		List<BankDetail> bankDetailList = Lists.newArrayList();
		bankDetailList.add(bankDetail1);
		bankDetailList.add(bankDetail2);
		bankDetailDao.insertBatch(bankDetailList);
	}

	@Test
	public void listBankDetail() {
	}
}