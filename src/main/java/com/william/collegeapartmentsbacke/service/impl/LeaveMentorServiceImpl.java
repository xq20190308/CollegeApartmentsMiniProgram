package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.collegeapartmentsbacke.pojo.entity.LeaveMentor;
import com.william.collegeapartmentsbacke.service.LeaveMentorService;
import com.william.collegeapartmentsbacke.mapper.LeaveMentorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMentorServiceImpl implements LeaveMentorService {
    @Autowired
    private LeaveMentorMapper leaveMentorMapper;

    @Override
    public List<LeaveMentor> getAllLeaveMentors() {
        return leaveMentorMapper.selectList(null);
    }

    @Override
    public List<LeaveMentor> getByPostId(String postId) {
        QueryWrapper<LeaveMentor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",postId);
        return leaveMentorMapper.selectList(queryWrapper);
    }

    @Override
    public Integer addLeaveMentors(List<LeaveMentor> list) {
        return leaveMentorMapper.addLeaveMentors(list);
    }


}
