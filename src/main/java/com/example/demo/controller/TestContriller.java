package com.example.demo.controller;

import com.example.demo.entity.Suggestion;
import com.example.demo.mapper.SuggestionMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestContriller {
    @Resource
    SuggestionMapper suggestionMapper;
    @GetMapping("/suggestions")
    public List<Suggestion> getsuggestion() {
        return suggestionMapper.findall();
    }
    @PostMapping("/suggestions")
    public String addSuggestion(@RequestBody Suggestion suggestion) {
        suggestionMapper.save(suggestion);
        return "success";
    }


    @DeleteMapping("/suggestions{id}")
    public String deleteSuggestion(@PathVariable ("id") long id) {
        suggestionMapper.delete(id);
        return "success";
    }
}
