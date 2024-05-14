package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.Suggestion;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SuggestionService {

    void SubmitSuggestion(Suggestion suggestion);

    //查询草稿
    List<Suggestion> SelectDraftfindall();

    List<Suggestion> Selectfindall();

    void Savedaft(Suggestion suggestion);

    boolean deleteSuggestion(long id);

    String uploadFile(@RequestParam("file") MultipartFile file);
}
