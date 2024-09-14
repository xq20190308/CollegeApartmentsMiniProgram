package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;

import java.util.List;

public interface LeavePostService {
    public List<LeavePost> getAllLeavePosts();

    public int insertLeavePost(LeavePost leavePost);

    public List<LeavePost> getByReviewerId(String reviewerId);

    public List<LeavePost> getByUserId(String userId);
}
