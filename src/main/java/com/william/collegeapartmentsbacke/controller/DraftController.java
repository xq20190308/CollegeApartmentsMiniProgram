package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.mapper.SuggestionMapper;
import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DraftController {
    @Resource
    SuggestionMapper suggestionMapper;
    @PostMapping("/suggestionsdraft")
    public Result addDraft(@RequestBody Suggestion suggestion) {
        suggestionMapper.savedaft(suggestion);
        return Result.success();
    }
}
