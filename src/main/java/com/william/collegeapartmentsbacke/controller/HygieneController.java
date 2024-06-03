package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HygieneController {
    @Autowired
    private HygieneService hygieneService;

    @SneakyThrows
    @PostMapping("/updateData")
    public List<Hygiene> updateData(@RequestParam("file") MultipartFile file) {
        List<Hygiene> p=hygieneService.SaveRank(file.getInputStream());
        hygieneService.upData(p);
        return p;
    }
    @GetMapping("/SelesctRank/{id}")
    public Result SelesctRank(@PathVariable("id") String id) {
        String Rank=hygieneService.SelectRank(id);
        return Result.success(Rank);
    }

}
