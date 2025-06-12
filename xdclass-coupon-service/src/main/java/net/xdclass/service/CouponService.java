package net.xdclass.service;

import net.xdclass.model.CouponDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duruijuan
 * @since 2025-06-12
 */
public interface CouponService {
    /**
     * description:分页查询优惠券
     * @param page
     * @param size
     * @return Map<String, Object>
     * @author: duruijuan
     * @since: 2025-06-12 16:48
     **/
    Map<String ,Object> pageCouponActivity(int page,int size);

}
