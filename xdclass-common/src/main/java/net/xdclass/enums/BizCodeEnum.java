package net.xdclass.enums;

import lombok.Getter;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.enums
 * @className: BizCodeEnum
 * @author: duruijuan
 * @description:状态码定义约束，共6位数，前三位代表服务，后4位代表接⼝，⽐如商品服务210,购物⻋是220、⽤户服务230，403代表权限
 * @since: 2025-06-04 14:44
 * @version: 1.0
 */
public enum BizCodeEnum {
    /**
     * 通⽤操作码
     */
    OPS_REPEAT(110001,"重复操作"),
    /**
     *验证码
     */
    CODE_TO_ERROR(240001,"接收号码不合规"),
    CODE_LIMITED(240002,"验证码发送过快"),
    CODE_ERROR(240003,"验证码错误"),
    CODE_CAPTCHA_ERROR(240101,"图形验证码错误"),
    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001,"账号已经存在"),
    ACCOUNT_UNREGISTER(250002,"账号不存在"),
    ACCOUNT_PWD_ERROR(250003,"账号或者密码错误");
    @Getter
    private int code;
    @Getter
    private String message;
    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
