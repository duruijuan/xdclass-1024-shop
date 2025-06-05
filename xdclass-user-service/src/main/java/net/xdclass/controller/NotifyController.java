package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

}
