package com.demo.controller;

import com.demo.service.ContentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by heWei on 2018/8/3
 * 单常用元测试类模板
 * 参考文档: https://spring.io/guides/gs/testing-restdocs/
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private ContentsService contentsService;

    @Test
    public void hello() {

        System.out.println("hello world");

    }

    @Test
    public void findAll() {

        System.out.println(contentsService.findAll());

    }



}
