package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.entity.LeaveMentor;

import java.util.List;

public interface LeaveMentorService {
    List<LeaveMentor> getAllLeaveMentors();

    List<LeaveMentor> getByPostId(String postId);

    Integer addLeaveMentors(List<LeaveMentor> list);
}
