package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface SuggestionService {

    void SubmitSuggestion(Suggestion suggestion);

    //查询草稿

    List<Suggestion> SelectDraftfindall(String stu_id);


    List<Suggestion> Selectfindall();

    String Savedaft(Suggestion suggestion);

    boolean deleteSuggestion(long id);

    String Selectfile(String id);

}
