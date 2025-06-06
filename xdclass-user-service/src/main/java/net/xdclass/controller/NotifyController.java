package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.controller
 * @className: NotifyController
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 14:34
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/user/v1")
@Api(tags = "通知模块")
@Slf4j
public class NotifyController {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private StringRedisTemplate redisTemplate;
// 图形验证码有效期为10分钟
    private static final long CAPTCHA_CODE_EXPIRED=60*1000*10;
    /**
     * description:获取图形验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @author: duruijuan
     * @since: 2025-06-05 15:07
     **/
    @GetMapping("/captcha")
    @ApiOperation("获取图形验证码")
    public void getCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String captchaText=captchaProducer.createText();
        log.info("图形验证码:{}",captchaText);
        //存储
        redisTemplate.opsForValue().set(getCaptchaKey(httpServletRequest),captchaText,CAPTCHA_CODE_EXPIRED, TimeUnit.MICROSECONDS);
        BufferedImage bufferedImage=captchaProducer.createImage(captchaText);
        ServletOutputStream outputStream=null;
      try{
          outputStream=httpServletResponse.getOutputStream();
          ImageIO.write(bufferedImage,"jpg",outputStream);
          outputStream.flush();
          outputStream.close();

      }catch (IOException e){
          log.error("获取图形验证码异常:{}",e);

      }

    }
/**
 * description:获取缓存的key
 * @param request
 * @return {@link String}
 * @author: duruijuan
 * @since: 2025-06-05 20:04
 **/
    private String getCaptchaKey(HttpServletRequest request){
        String ip= CommonUtil.getIpAddr(request);
        String userAgent=request.getHeader("User-Agent");
        String key="user-service:captcha:"+CommonUtil.MD5(ip+userAgent);
        log.info("ip={}",ip);
        log.info("userAgent={}",userAgent);
        log.info("key={}",key);
        return key;
    }

}
