package net.xdclass.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.config
 * @className: MybatisPlusPageConfig
 * @author: duruijuan
 * @description:
 * @since: 2025-06-12 16:18
 * @version: 1.0
 */
@Configuration
public class MybatisPlusPageConfig {
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor(){
//        return new PaginationInnerInterceptor();
//    }
    /**
     * description:新版分页插件配置
     * @param
     * @return MybatisPlusInterceptor
     * @author: duruijuan
     * @since: 2025-06-12 16:39
     **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
