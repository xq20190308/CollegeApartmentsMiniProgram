package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;
import com.william.collegeapartmentsbacke.pojo.entity.Result;

import java.util.List;

public interface LeavePostService {
    public List<LeavePost> getAllLeavePosts();

    public int insertLeavePost(LeavePost leavePost);

    Result updateLeavePost(LeavePost leavePost);

    public List<LeavePost> getByReviewerId(String reviewerId);

    public List<LeavePost> getByUserId(String userId);
}
