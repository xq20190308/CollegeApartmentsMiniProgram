package com.william.collegeapartmentsbacke.service.impl;

import com.william.collegeapartmentsbacke.mapper.SuggestionMapper;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import com.william.collegeapartmentsbacke.pojo.Uploadfile;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;

@Slf4j
@Service
public class SuggesitionServicelmpl implements SuggestionService {
    @Autowired
    private SuggestionMapper suggestionmapper;

    //提交投诉
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void SubmitSuggestion(Suggestion suggestion) {
        LocalDateTime pushtime=LocalDateTime.now();
        pushtime = pushtime.withNano(0);
        suggestion.setPushtime(pushtime);
        suggestionmapper.savedaft(suggestion);
        suggestionmapper.submit(suggestion);
    }

    //查询草稿
    @Override
    public List<Suggestion> SelectDraftfindall()
    {
        return suggestionmapper.Draftfindall();
    }

   //查询用户投诉
    @Override
    public List<Suggestion> Selectfindall()
    {
        return suggestionmapper.findall();
    }

    //保存草稿
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer Savedaft(Suggestion suggestion)
    {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime pushtime=LocalDateTime.now();
        pushtime = pushtime.withNano(0);
        suggestion.setPushtime(pushtime);
        suggestionmapper.savedaft(suggestion);
        Integer id=suggestionmapper.selectLast();
        return suggestionmapper.selectLast();
    }


    //删除草稿
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSuggestion(long id)
    {
        if(suggestionmapper.Count()>0) {
            suggestionmapper.delete(id);
            return true;
        }
        else
        {
            return false;
        }
    }


    //上传文件
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void Savefile(Uploadfile file) {
        suggestionmapper.savefile(file);
    }


    //获取文件
    @Override
    public String Selectfile(String id) {
        return suggestionmapper.selectfile(id);
    }

}
