package com.william.collegeapartmentsbacke.controller;

import com.william.collegeapartmentsbacke.pojo.dto.HygieneDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Hygiene;
import com.william.collegeapartmentsbacke.service.HygieneService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api")
public class HygieneController {
    @Autowired
    private HygieneService hygieneService;

    @SneakyThrows
    @PostMapping("/updateData")
    public Set<Hygiene> updateData(@RequestParam("file") MultipartFile file,String weeks) {
        Set<Hygiene> p=hygieneService.SaveRank(file.getInputStream());
        hygieneService.upData(p,weeks);
        return p;
    }
    @GetMapping("/SelectRank/{id}")
    public Result SelesctRank(@PathVariable String id) {
        HygieneDTO Rank=hygieneService.SelectRank(id);
        log.info(Rank.toString());
        return Result.success(Rank);
    }
}
