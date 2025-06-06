package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service.impl
 * @className: MailServiceImpl
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 21:01
 * @version: 1.0
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
   // springboot提供的一个发送邮件的简单对象，直接注入即可
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    /**
     * description:发送邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @return
     * @author: duruijuan
     * @since: 2025-06-05 21:09
     **/
    @Override
    public void sendMail(String to, String subject, String content) {
       // 创建一个邮箱消息对象
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        //配置邮箱发送人
        mailMessage.setFrom(from);
        //配置邮件收件人
        mailMessage.setTo(to);
        //邮件的主题
        mailMessage.setSubject(subject);
        //邮件的内容
        mailMessage.setText(content);
        mailSender.send(mailMessage);
        log.info("邮件发送成功:{}",mailMessage.toString());
    }
}
