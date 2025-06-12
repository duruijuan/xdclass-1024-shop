package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.Model.LoginUser;
import net.xdclass.enums.AddressStatusEnum;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.model.AddressDO;
import net.xdclass.model.AddressVO;
import net.xdclass.request.AddressAddRequest;
import net.xdclass.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service.impl
 * @className: AddressServiceImpl
 * @author: duruijuan
 * @description:
 * @since: 2025-06-03 21:06
 * @version: 1.0
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * description:
     *
     * @param id
     * @return AddressDO
     * @author: duruijuan
     * @since: 2025-06-06 13:48
     **/
    @Override
    public AddressVO detail(Long id) {
        LoginUser loginUser= LoginInterceptor.threadLocal.get();
        AddressDO addressDO = addressMapper.selectOne(new QueryWrapper<AddressDO>()
                .eq("id", id)
                .eq("user_id",loginUser.getId()));
        if(addressDO==null){
            return null;
        }
        AddressVO addressVO=new AddressVO();
        BeanUtils.copyProperties(addressDO,addressVO);
        return addressVO;
    }

    /**
     * description:新增收货地址
     *
     * @param addressAddRequest
     * @return
     * @author: duruijuan
     * @since: 2025-06-11 15:21
     **/
    @Override
    public void add(AddressAddRequest addressAddRequest) {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        AddressDO addressDO = new AddressDO();
        addressDO.setCreateTime(new Date());
        addressDO.setUserId(loginUser.getId());
        BeanUtils.copyProperties(addressAddRequest, addressDO);
        //是否有默认收货地址
        if (addressDO.getDefaultStatus() ==  AddressStatusEnum.DEFAULT_STATUS.getStatus()) {
            //查找数据库是否有默认地址
            AddressDO defaultAddressDO = addressMapper.selectOne(new QueryWrapper<AddressDO>()
                    .eq("user_id", loginUser.getId())
                    .eq("default_status", AddressStatusEnum.DEFAULT_STATUS.getStatus()));
            if (defaultAddressDO != null) {
                //修改为非默认收货地址
                defaultAddressDO.setDefaultStatus(AddressStatusEnum.COMMON_STATUS.getStatus());
                addressMapper.update(defaultAddressDO, new QueryWrapper<AddressDO>()
                        .eq("id", defaultAddressDO.getId()) );
            }
        }
        int rows = addressMapper.insert(addressDO);

        log.info("新增收货地址:rows={},data={}", rows, addressDO);

    }
    /**
     * description:根据id删除指定地址
     * @param addressId
     * @return int
     * @author: duruijuan
     * @since: 2025-06-11 20:29
     **/
    @Override
    public int delete(int addressId) {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        int rows=addressMapper.delete(new QueryWrapper<AddressDO>().eq("id",addressId).eq("user_id",loginUser.getId()));
        return rows;
    }
    /**
     * description:查询用户的全部收货地址
     * @param
     * @return List<AddressVO>
     * @author: duruijuan
     * @since: 2025-06-11 20:50
     **/
    @Override
    public List<AddressVO> listUserAllAddress() {
        LoginUser loginUser=LoginInterceptor.threadLocal.get();
        List<AddressDO> list=addressMapper.selectList(new QueryWrapper<AddressDO>().eq("user_id",loginUser.getId()));
        //lamba表达式
        List<AddressVO> addressDOList=list.stream().map(obj->{
            AddressVO addressVO=new AddressVO();
            BeanUtils.copyProperties(obj,addressVO);
            return addressVO;
        }).collect(Collectors.toList());
        return addressDOList;
    }
}
