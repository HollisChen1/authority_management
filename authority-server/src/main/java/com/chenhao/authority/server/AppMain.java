package com.chenhao.authority.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/24 22:10
 */
@SpringBootApplication
@ComponentScan({"com.chenhao.authority.core","com.chenhao.authority.server.configuration"})
@MapperScan("com.chenhao.authority.core.mapper")
@EnableDiscoveryClient
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}
