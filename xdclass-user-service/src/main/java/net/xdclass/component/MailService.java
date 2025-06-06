package net.xdclass.component;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service
 * @className: MailService
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 20:59
 * @version: 1.0
 */
public interface MailService {
    /**
     * description:发送邮件
     * @param to
     * @param subject
     * @param content
     * @return
     * @author: duruijuan
     * @since: 2025-06-05 21:01
     **/
     void sendMail(String to,String subject,String content);
}
