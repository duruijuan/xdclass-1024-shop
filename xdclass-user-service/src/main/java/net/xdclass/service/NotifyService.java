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
    JsonData sendCode(SendCodeEnum sendCodeEnum,String  to);
}
