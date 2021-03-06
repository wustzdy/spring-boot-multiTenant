package com.wust.spring.boot.multi.tenant;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.wust.spring.boot.multi.tenant.bean.mapper")
public class SpringBootMultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiTenantApplication.class, args);
    }
}
