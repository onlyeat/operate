package cn.stylefeng.guns;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuqianyi
 * @date 2019-02-15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTests.class})
public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Test
    public void testOne() {
        System.out.println("test hello 1");
    }

    @Test
    public void testTwo() {
        System.out.println("test hello 2");
        TestCase.assertEquals(1, 1);
    }


    @Test
    public void testString() {
        String testStr = "flight_dynamic_car_push_0_0_1";
        String modelStr = "flight_dynamic_car_push_";
        int length = modelStr.length();
        String substring = testStr.substring(length);
        System.out.println("idx=" + length);
        System.out.println("substring=" + substring);

        int i = 1;
        int j = 3;
        double jj = i + j;
        System.out.println("jj=" + jj);
    }

    @Before
    public void testBefore() {
        this.setTime(System.currentTimeMillis());
        logger.info("==> 测试开始执行 <==");
    }

    @After
    public void testAfter() {
        logger.info("==> 测试执行完成，耗时：{} <==",
                System.currentTimeMillis() - this.getTime() + "毫秒");
    }
}
