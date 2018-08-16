package com.demo.service;


import java.util.List;
import java.util.Map;

/**
 * Created by heWei on 2018/7/5
 */
public interface ContentsService {

    List<Map<String,Object>> findAll();


    List<Map<String,Object>> findByUid(Integer uid);
}
