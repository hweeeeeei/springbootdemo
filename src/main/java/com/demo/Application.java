package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by heWei on 2018/7/3
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.demo.controller", "com.demo.serviceImp"})  //入口类放在根目录会自动扫描下面的注解
@MapperScan("com.demo.mapper") //mybatis 扫描mapper接口, 不用在每个mapper中都添加注解
@Slf4j
public class Application {

    //  该main()方法使用Spring Boot的SpringApplication.run()方法启动应用程序。
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        log.info("启动完成");
    }

}




/**
 @SpringBootApplication 是一个便利注释，添加了以下所有内容：
 @Configuration 标记该类作为应用程序上下文的bean定义的源。
 @EnableAutoConfiguration 告诉Spring Boot开始根据类路径设置，其他bean和各种属性设置添加bean。
 通常你会添加@EnableWebMvc一个Spring MVC应用程序，但Spring Boot会在类路径上看到spring-webmvc时自动添加它。这会将应用程序标记为Web应用程序并激活关键行为，例如设置a DispatcherServlet。
 @ComponentScan 告诉Spring在包中寻找其他组件，配置和服务hello，允许它找到HelloController。**/