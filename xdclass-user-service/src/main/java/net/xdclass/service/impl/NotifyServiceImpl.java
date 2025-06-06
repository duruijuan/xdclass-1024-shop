package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.component.MailService;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CheckUtil;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service.impl
 * @className: NotifyServiceImpl
 * @author: duruijuan
 * @description:
 * @since: 2025-06-06 14:36
 * @version: 1.0
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private MailService mailService;
    //验证码标题
    private static final String SUBJECT="小滴课堂验证码";
    //验证码内容
    private static final String CONTENT="您的验证码是%s,有效时间是60秒，不要告诉别人";
    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {
        if(CheckUtil.isEmail(to)){
            //邮箱验证码
            String code=CommonUtil.getRandomCode(6);
            mailService.sendMail(to,SUBJECT,String.format(CONTENT,code));
            return JsonData.buildSuccess();
        }else if(CheckUtil.isPhone(to)){
            //短信验证码
        }
        return null;
    }
}
