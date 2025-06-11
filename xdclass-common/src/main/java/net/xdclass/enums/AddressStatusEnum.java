package net.xdclass.enums;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.enums
 * @className: AddressStatusEnum
 * @author: duruijuan
 * @description:
 * @since: 2025-06-11 17:27
 * @version: 1.0
 */
public enum AddressStatusEnum {
    /**
     * 默认收货地址
     **/
    DEFAULT_STATUS(1),
    /**
     * 非默认收货地址
     **/
    COMMON_STATUS(0);
    private int status;
    private AddressStatusEnum(int status){
        this.status=status;

    }

    public int getStatus() {
        return status;
    }
}
