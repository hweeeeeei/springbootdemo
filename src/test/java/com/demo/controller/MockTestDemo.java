package com.demo.controller;

import com.demo.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by heWei on 2018/8/3
 *  mockmvc  API（Controller）单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class MockTestDemo {

    @Autowired
    private ContentsController contentsController;

    private MockMvc mvc;

    @Before
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(contentsController).build();

    }

    //不带参数发送请求
    @Test
    public void contentsFindAll() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/contents/findAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }


    //带参数发送请求
    @Test
    public void contentsFindByUid() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/contents/findByUid").accept(MediaType.APPLICATION_JSON)
                .param("uid", "42234"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

}
