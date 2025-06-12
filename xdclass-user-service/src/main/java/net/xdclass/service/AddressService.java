package net.xdclass.service;

import net.xdclass.model.AddressDO;
import net.xdclass.model.AddressVO;
import net.xdclass.request.AddressAddRequest;

import java.util.List;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service
 * @className: AddressService
 * @author: duruijuan
 * @description:
 * @since: 2025-06-03 21:05
 * @version: 1.0
 */
public interface AddressService {
    /**
     * description:查找指定收货详情
     * @param id
     * @return AddressDO
     * @author: duruijuan
     * @since: 2025-06-11 15:18
     **/
    AddressVO detail(Long id);
    /**
     * description:新增收货地址
     *
     * @param addressAddRequest
     * @return
     * @author: duruijuan
     * @since: 2025-06-11 15:18
     **/
    void add(AddressAddRequest addressAddRequest);
    /**
     * description:根据id删除指定地址
     * @param addressId
     * @return int
     * @author: duruijuan
     * @since: 2025-06-11 20:28
     **/
    int delete(int addressId);
    /**
     * description:查询用户的全部收货地址
     * @param
     * @return List<AddressVO>
     * @author: duruijuan
     * @since: 2025-06-11 20:50
     **/
    List<AddressVO> listUserAllAddress();
}
