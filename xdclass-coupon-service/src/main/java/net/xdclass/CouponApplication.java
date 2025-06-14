package net.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass
 * @className: CouponApplication
 * @author: duruijuan
 * @description:
 * @since: 2025-06-03 19:10
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("net.xdclass.mapper")

public class CouponApplication {
    public static void main(String [] args){

        SpringApplication.run(CouponApplication.class,args);
    }

}
