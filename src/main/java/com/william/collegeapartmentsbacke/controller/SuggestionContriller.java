package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.Result;
import com.william.collegeapartmentsbacke.pojo.Suggestion;
import com.william.collegeapartmentsbacke.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuggestionContriller {
    @Autowired
    private SuggestionService suggestionService;
    @GetMapping("/suggestions")
    public List<Suggestion> Selectfindall() {
        return suggestionService.Selectfindall();
    }



    @PostMapping("/suggestions")
    public Result SubmitSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.SubmitSuggestion(suggestion);
        return Result.success();
    }


    @PostMapping("/suggestionsdraft")
    public Result Savedaft(@RequestBody Suggestion suggestion) {
        suggestionService.Savedaft(suggestion);
        return Result.success();
    }


    @DeleteMapping("/suggestions{id}")
    public Result deleteSuggestion(@PathVariable ("id") long id) {
        suggestionService.deleteSuggestion(id);
        if(suggestionService.deleteSuggestion(id)) {
            return Result.success();
        }
        else
            return Result.error("Draft is empty");
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String result = suggestionService.uploadFile(file);
        if(result.equals("success"))
            return Result.success();
        else if(result.equals("file upload fail")||result.equals("file is empty"))
            return Result.error(result);
        else return null;
    }

}
