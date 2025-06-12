package net.xdclass.biz;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.UserApplication;
import net.xdclass.model.AddressDO;
import net.xdclass.model.AddressVO;
import net.xdclass.service.AddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.biz
 * @className: AddressTest
 * @author: duruijuan
 * @description:
 * @since: 2025-06-05 11:05
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;
    /**
     * description:
     * @param
     * @return void
     * @author: duruijuan
     * @since: 2025-06-06 13:50
     **/
    @Test
    public void testAddressDetail(){
        AddressVO addressVO=addressService.detail(1L);
        log.info(addressVO.toString());
        Assert.assertNotNull(addressVO);
    }
}
