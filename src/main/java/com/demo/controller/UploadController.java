package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by heWei on 2018/7/30
 */
//上传示例
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    //跳转到 templates 目录下的 upload.html
    @PostMapping(value = "/upload.html")
    public String upload(){

        return "upload";
    }

    @PostMapping(value = "/upDemo")
    @ResponseBody
    public Object upDemo(@RequestParam("file") MultipartFile file,
    HttpServletRequest request){

        return "success";
    }




}
