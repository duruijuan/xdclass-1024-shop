package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.constant.CacheKey;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.component.MailService;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CheckUtil;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * description:验证码10分钟有效
     * @author: duruijuan
     * @since: 2025-06-06 16:53
     **/
    private static final int CODE_EXPIRED=60*1000*10;
   /**
    * description:
    * 前置：判断是否重复发送
    * 1.存储验证码到缓存
    * 2.发送邮箱验证码
    * 后置：存储发送记录
    * @param sendCodeEnum
    * @param to
    * @return JsonData
    * @author: duruijuan
    * @since: 2025-06-06 16:17
    **/
    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {
        String cacheKey=String.format(CacheKey.CHECK_CODE_KEY,sendCodeEnum.name(),to);
        String cacheValue=redisTemplate.opsForValue().get(cacheKey);
        //如果不为空，则判断是否60秒内重复发送
        if(StringUtils.isNotBlank(cacheValue)){
            long ttl=Long.parseLong(cacheValue.split("_")[1]);
            //当前时间戳-发送验证码时间戳，如果小于60秒，则不给重复发送
            if(CommonUtil.getCurrentTimestamp()-ttl<60*1000){
                log.info("重复发送验证码，时间间隔:{}",(CommonUtil.getCurrentTimestamp()-ttl)/1000);
                return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
            }
        }
        //拼接验证码
        String code=CommonUtil.getRandomCode(6);
        String value=code+"_"+CommonUtil.getCurrentTimestamp();
        redisTemplate.opsForValue().set(cacheKey,value,CODE_EXPIRED, TimeUnit.MICROSECONDS);
        if(CheckUtil.isEmail(to)){
            //邮箱验证码
            mailService.sendMail(to,SUBJECT,String.format(CONTENT,code));
            return JsonData.buildSuccess();
        }else if(CheckUtil.isPhone(to)){
            //短信验证码
        }
        return null;
    }
}
