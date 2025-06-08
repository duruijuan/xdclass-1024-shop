package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.component.MailService;
import net.xdclass.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.db
 * @className: MD5Test
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 14:04
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class MD5Test {

    @Test
    public void testMD5(){
        log.info(CommonUtil.MD5("123456"));
    }
}
