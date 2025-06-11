package net.xdclass.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.model
 * @className: UserVO
 * @author: duruijuan
 * @description:
 * @since: 2025-06-11 11:23
 * @version: 1.0
 */
@Data
public class UserVO {
    private Long id;

    /**
     * 昵称
     */
    private String name;


    /**
     * 头像
     */
    @JsonProperty("head_img")
    private String headImg;

    /**
     * ⽤户签名
     */
    private String slogan;

    /**
     * 0表示	⼥，1表示男
     */
    private Integer sex;

    /**
     * 积	分
     */
    private Integer points;

    private Date createTime;

    /**
     * 邮	箱
     */
    private String mail;




}
