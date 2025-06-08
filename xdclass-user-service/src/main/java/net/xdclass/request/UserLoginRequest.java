package net.xdclass.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.request
 * @className: UserLoginRequest
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 15:30
 * @version: 1.0
 */
@Data
@ApiModel(value = "用户登录对象",description = "用户登录请求对象")
public class UserLoginRequest {
    @ApiModelProperty(value = "邮箱",example = "62647363666@qq.com")
    private String mail;
    @ApiModelProperty(value = "密码",example = "123456")
    private String pwd;
}
