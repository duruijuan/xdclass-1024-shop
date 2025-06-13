package net.xdclass.config;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.config
 * @className: InterceptorConfig
 * @author: duruijuan
 * @description:
 * @since: 2025-06-11 10:58
 * @version: 1.0
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
            //拦截路径
            .addPathPatterns("/api/coupon/*/**","/api/coupon_record/v1/*/**")
            //不拦截路径，放行路径
            .excludePathPatterns("api/coupon/*/page_coupon");


    }
}
