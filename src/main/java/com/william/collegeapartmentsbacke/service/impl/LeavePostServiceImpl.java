package com.william.collegeapartmentsbacke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.william.collegeapartmentsbacke.mapper.LeavePostMapper;
import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;
import com.william.collegeapartmentsbacke.service.LeavePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeavePostServiceImpl implements LeavePostService {

    @Autowired
    private LeavePostMapper leavePostMapper;
    @Override
    public List<LeavePost> getAllLeavePosts() {
        return leavePostMapper.selectList(null);
    }

    @Override
    public int insertLeavePost(LeavePost leavePost) {
        return leavePostMapper.insert(leavePost);
    }

    @Override
    public int updateLeavePost(LeavePost leavePost) {
        return leavePostMapper.updateById(leavePost);
    }

    @Override
    public List<LeavePost> getByReviewerId(String reviewerId) {
        QueryWrapper<LeavePost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewer_id",reviewerId);
        return leavePostMapper.selectList(queryWrapper);
    }

    @Override
    public List<LeavePost> getByUserId(String userId) {
        QueryWrapper<LeavePost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        //按照创建时间降序
        queryWrapper.orderByAsc("created_at");
        return leavePostMapper.selectList(queryWrapper);
    }
}
