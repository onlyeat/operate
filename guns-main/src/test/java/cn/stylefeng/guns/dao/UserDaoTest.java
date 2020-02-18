package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.BaseTest;
import cn.stylefeng.guns.modular.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/18/23:13
 * @Description:
 */
public class UserDaoTest extends BaseTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void saveBatch() {
		System.out.println("1".equals(null));
		System.out.println(StringUtils.isNumeric(null));

		String name = "刘谦益";
		List<User> users = Lists.newArrayList();
		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setUserName(String.format("%s%d", name, i));
			users.add(user);
		}
		userDao.saveBatch(users);
	}
}