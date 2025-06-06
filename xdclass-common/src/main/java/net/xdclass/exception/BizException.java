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
public class BizException extends
        RuntimeException {
    private Integer code;
    private String msg;
    public BizException(Integer code, String
            message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }
}
