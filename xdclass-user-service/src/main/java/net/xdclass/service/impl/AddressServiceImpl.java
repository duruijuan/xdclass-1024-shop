package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.xdclass.mapper.AddressMapper;
import net.xdclass.model.AddressDO;
import net.xdclass.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public AddressDO detail(Long id) {
        AddressDO addressDO=addressMapper.selectOne(new QueryWrapper<AddressDO>().eq("id",id));
        return addressDO;
    }
}
