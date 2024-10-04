package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.LeavePost;

import java.util.List;

public interface LeavePostService {
    List<LeavePost> getAllLeavePosts();

    int insertLeavePost(LeavePost leavePost);

    AjaxResult updateLeavePost(LeavePost leavePost);

    List<LeavePost> getByReviewerId(String reviewerId);

    List<LeavePost> getByUserId(String userId);
}
