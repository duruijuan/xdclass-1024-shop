package net.xdclass.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.Model
 * @className: LoginUser
 * @author: duruijuan
 * @description:
 * @since: 2025-06-08 16:49
 * @version: 1.0
 */
@Data
public class LoginUser {
    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 头像
     */
    @JsonProperty ("head_img")
    private String headImg;
    /**
     * 邮	箱
     */
    private String mail;
}
