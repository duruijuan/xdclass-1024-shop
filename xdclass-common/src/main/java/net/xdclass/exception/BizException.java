package net.xdclass.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.xdclass.enums.BizCodeEnum;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.exception
 * @className: BizException
 * @author: duruijuan
 * @description:
 * @since: 2025-06-04 17:30
 * @version: 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{
    private int code;
    private String msg;
    public BizException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code=bizCodeEnum.getCode();
        this.msg=bizCodeEnum.getMessage();
    }
}
