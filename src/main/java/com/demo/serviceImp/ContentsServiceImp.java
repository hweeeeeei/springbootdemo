package com.demo.serviceImp;

import com.demo.mapper.ContentsMapper;
import com.demo.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by heWei on 2018/7/5
 */
@Service
public class ContentsServiceImp implements ContentsService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Override
    public List<Map<String, Object>> findAll() {
        return contentsMapper.findAll();
    }

    @Override
    public List<Map<String, Object>> findByUid(Integer uid) {
        return contentsMapper.findByUid(uid);
    }
}
