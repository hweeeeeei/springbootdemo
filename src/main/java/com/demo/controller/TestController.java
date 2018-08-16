package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by heWei on 2018/7/3
 */
@RestController    // 该注解包含了 @Controller  @ResponseBody
@Slf4j //对get日志类的封装
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/log")
    String log() {
        log.info("log info 11111");
        log.error("log error 22222");
        log.warn("log warn 33333333");
        log.debug("log debug 444444");
        return "hello_log";
    }

    @GetMapping("/jsonDemo")
    public Object jsonDemo() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", "1");
        map.put("test332", "332");
        map.put("test3", "13");
        map.put("test1", "11");
        map.put("hewei", "1");
        return map;

    }

}