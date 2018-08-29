package com.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by heWei on 2018/7/4
 */
public interface ContentsMapper {

//    @Select("SELECT u_id FROM contents")
    List<Map<String,Object>> findAll();


    List<Map<String,Object>> findByUid(@Param("uid") Integer uid);

}
