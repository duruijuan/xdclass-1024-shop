package net.xdclass.exception;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.exception
 * @className: ExceptionHandler
 * @author: duruijuan
 * @description:
 * @since: 2025-06-04 17:38
 * @version: 1.0
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){
     //是不是自定义异常
        if(e instanceof BizException){
            BizException bizException=(BizException) e;
            log.error("[业务异常 {}]",e);
            return JsonData.buildCodeAndMsg(bizException.getCode(),bizException.getMsg());
        }else{
            log.error("[系统异常 {}]",e);
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
