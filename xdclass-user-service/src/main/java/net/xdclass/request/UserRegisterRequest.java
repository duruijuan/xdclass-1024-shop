package net.xdclass.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.request
 * @className: UserRegisterRequest
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 12:59
 * @version: 1.0
 */
@Data
@ApiModel(value = "用户注册对象",description = "用户注册请求对象")
public class UserRegisterRequest {
    @ApiModelProperty(value = "昵称",example = "琳达")
    private String name;
    @ApiModelProperty(value = "密码",example = "123")
    private String pwd;
    @ApiModelProperty(value = "头像",example = "123")
    @JsonProperty("head_img")
    private String headImg;
    @ApiModelProperty(value = "用户个性签名",example = "人生需要动态规划，学习需要贪心算法")
    private String slogan;
    @ApiModelProperty(value = "0表示⼥，1表示男",example = "1")
    private String sex;
    @ApiModelProperty(value = "邮箱",example = "62647363666@qq.com")
    private String mail;
    @ApiModelProperty(value = "验证码",example = "6264")
    private String code;
}
