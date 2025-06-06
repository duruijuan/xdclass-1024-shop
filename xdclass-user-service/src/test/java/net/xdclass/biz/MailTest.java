package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.biz
 * @className: MailTest
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 21:10
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class MailTest {
    @Autowired
    private MailService mailService;
    @Test
    public void testSendMail(){
        mailService.sendMail("1770136104@qq.com","欢迎注册邮箱","申请注册邮箱");
    }
}
