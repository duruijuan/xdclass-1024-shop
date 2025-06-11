package net.xdclass.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.model
 * @className: AddressVO
 * @author: duruijuan
 * @description:
 * @since: 2025-06-11 20:09
 * @version: 1.0
 */
@Data
public class AddressVO {
    private Long id;

    /**
     * ⽤户id
     */
    private Long userId;

    /**
     * 是否默认收货地址：0->否；1->是
     */
    @JsonProperty("default_status")
    private Integer defaultStatus;

    /**
     * 收发货⼈姓名
     */
    @JsonProperty("receive_name")
    private String receiveName;

    /**
     * 收货⼈电话
     */
    private String phone;

    /**
     * 省/直辖市
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 详细地址
     */
    @JsonProperty("detail_address")
    private String detailAddress;



}
