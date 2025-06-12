package net.xdclass.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.model.AddressDO;
import net.xdclass.model.AddressVO;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 电商-公司收发货地址表 前端控制器
 * </p>
 *
 * @author duruijuan
 * @since 2025-06-03
 */
@Api(tags = "收货地址模块")
@RestController
@RequestMapping("/api/address/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation("根据id查找地址详情")
    @GetMapping("/find/{address_id}")
    public Object detail(@ApiParam(value = "地址id", required = true)
                         @PathVariable("address_id") long addressId) {
        AddressVO addressVO = addressService.detail(addressId);

        return addressVO == null ? JsonData.buildResult(BizCodeEnum.ADDRESS_NO_EXIT) : JsonData.buildSuccess(addressVO);
    }

    @ApiOperation("新增收货地址")
    @PostMapping("/add")
    public JsonData add(@ApiParam(value = "地址对象", required = true)
                        @RequestBody AddressAddRequest addressAddRequest) {
        addressService.add(addressAddRequest);
        return JsonData.buildSuccess();


    }

    /**
     * description:删除指定收货地址
     *
     * @param addressId
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-11 20:27
     **/
    @ApiOperation("删除收货地址")
    @DeleteMapping("/delete/{address_id}")
    public JsonData delete(@ApiParam(value = "地址id", required = true)
                           @PathVariable("address_id") int addressId) {
        int rows = addressService.delete(addressId);
        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildResult(BizCodeEnum.ADDRESS_DEL_FAIL);
    }
    /**
     * description:查询用户的全部收货地址
     * @param
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-11 20:48
     **/
    @ApiOperation("查询用户的全部收货地址")
    @GetMapping("/list")
    public JsonData findUserAllAddress(){
        List<AddressVO> list = addressService.listUserAllAddress();
        return JsonData.buildSuccess(list);

    }
}

