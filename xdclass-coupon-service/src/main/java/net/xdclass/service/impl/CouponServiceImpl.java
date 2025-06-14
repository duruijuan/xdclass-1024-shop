package net.xdclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.xdclass.enums.CouponCategoryEnum;
import net.xdclass.enums.CouponPublishEnum;
import net.xdclass.model.CouponDO;
import net.xdclass.mapper.CouponMapper;
import net.xdclass.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import net.xdclass.util.JsonData;
import net.xdclass.vo.CouponVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duruijuan
 * @since 2025-06-12
 */
@Service
public class CouponServiceImpl  implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Map<String, Object> pageCouponActivity(int page, int size) {
        Page<CouponDO> pageInfo=new Page<>(page,size);
       IPage<CouponDO> couponDOIPage=couponMapper.selectPage(pageInfo,new QueryWrapper<CouponDO>()
                .eq("publish", CouponPublishEnum.PUBLISH)
                .eq("category", CouponCategoryEnum.PROMOTION)
                .orderByDesc("create_time"));
        Map<String, Object> pageMap= new HashMap<>(3);
        //总记录数
        pageMap.put("total_record",couponDOIPage.getTotal());
        //总页数
        pageMap.put("total_page",couponDOIPage.getPages());
        pageMap.put("current_data",couponDOIPage.getRecords().stream().map(obj->beanProcess(obj))
                .collect(Collectors.toList()));
        return pageMap;
    }
    /**
     * description:领取优惠券接口
     * @param couponId
     * @param couponCategoryEnum
     * @return JsonData
     * @author: duruijuan
     * @since: 2025-06-13 22:09
     **/
    @Override
    public JsonData addCoupon(long couponId, CouponCategoryEnum couponCategoryEnum) {
        return JsonData.buildSuccess();
    }

    private CouponVO beanProcess(CouponDO couponDO) {
        CouponVO couponVO=new CouponVO();
        BeanUtils.copyProperties(couponDO,couponVO);
        return couponVO;


    }
}
