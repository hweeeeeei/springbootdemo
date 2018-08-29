package com.demo.controller;

import com.demo.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heWei on 2018/7/5
 */
@RestController
@RequestMapping(value = "/contents")
public class ContentsController {

    @Autowired
    private ContentsService contentsService;

    @GetMapping("/findByUid")
    Object findByUid (@RequestParam("uid") Integer uid){
        return contentsService.findByUid(uid);
    }

    //http://localhost:8080/demo/contents/findAll
    @GetMapping("/findAll")
    Object findAll() {
        return contentsService.findAll();
    }

}
