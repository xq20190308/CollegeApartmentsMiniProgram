package com.william.collegeapartmentsbacke.service;

import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface SuggestionService {

    void SubmitSuggestion(Suggestion suggestion);

    //查询草稿

    List<Suggestion> SelectDraftfindall(String pushtime);

    List<Suggestion> Selectfindall();

    String Savedaft(Suggestion suggestion);

    boolean deleteSuggestion(long id);

    void Savefile(Uploadfile file);

    String Selectfile(String id);

}
