package net.xdclass.service;

import net.xdclass.enums.SendCodeEnum;
import net.xdclass.util.JsonData;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service
 * @className: NotifyService
 * @author: duruijuan
 * @description:
 * @since: 2025-06-06 14:32
 * @version: 1.0
 */
public interface NotifyService {
    /**
     * description:发送验证码
     * @param sendCodeEnum
     * @param to
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-08 13:18
     **/
    JsonData sendCode(SendCodeEnum sendCodeEnum,String  to);
    /**
     * description:判断验证码是否一样
     * @param sendCodeEnum
     * @param to
     * @param code
     * @return boolean
     * @author: duruijuan
     * @since: 2025-06-08 13:19
     **/
    boolean checkCode(SendCodeEnum sendCodeEnum,String to,String code);
}
