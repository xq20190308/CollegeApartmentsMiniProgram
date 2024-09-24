package com.william.collegeapartmentsbacke.controller;
import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.Itemdata;
import com.william.collegeapartmentsbacke.pojo.entity.PageResults;
import com.william.collegeapartmentsbacke.pojo.entity.Result;
import com.william.collegeapartmentsbacke.pojo.entity.Suggestion;
import com.william.collegeapartmentsbacke.service.FileService;
import com.william.collegeapartmentsbacke.service.LostpropertyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class LostpropertyController {
    @Autowired
    LostpropertyService lostpropertyService;

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadItem")
    public Result saveFile(@RequestHeader("Authorization")String token, @RequestParam("file") List<MultipartFile> file, HttpServletRequest request)
    {
        return Result.success(fileService.Savefile(token,file,request));

    }

    @PostMapping("/addFound")
    public Result saveSubmit(@RequestBody Itemdata itemdata)
    {
            lostpropertyService.saveSubmit(itemdata);
            return Result.success();
    }

    @NoNeedLogin
    @GetMapping("/Getdata/{category}/{page}")
    public Result SelectData(@PathVariable("category") String category, @PathVariable("page") long page, HttpServletResponse response)
    {
        PageDTO pageDTO= new PageDTO();
        pageDTO.setNowPage(page);
        PageResults<Itemdata> pageResults=lostpropertyService.getItemData(category,pageDTO);
        List<Itemdata> list = pageResults.getList();
        response.setHeader("totalPage", String.valueOf(pageResults.getPageCount()));
        System.out.println("查询数据：" + list);
        System.out.println("查询总数：" +list.size());
        return Result.success(list);
    }

    @GetMapping("/getMydata/{id}")
    public Result SelectMydata(@PathVariable ("id") String id)
    {
        List<Itemdata> itemdata= lostpropertyService.getUserItemdata(id);
        return Result.success(itemdata);
    }
//    @GetMapping("/getydata/{category}")
//    public Result SelectData(@PathVariable("category") String category)
//    {
//        List<Itemdata> itemdata= lostpropertyService.getItemdata(category);
//        return Result.success(itemdata);
//    }

    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestBody Itemdata itemdata) {
        try {
            lostpropertyService.updateItemdata(itemdata);
            return Result.success();
        } catch (IllegalArgumentException e) {
        log.error("Invalid argument for update status", e);
        return Result.error("Invalid argument for update status");
        } catch (Exception e) {
        log.error("Failed to update status", e);
        return Result.error("Failed to update status");
        }
    }

   @PostMapping("/updateData/{id}")
    public Result updateData(@PathVariable("id") Integer id, Integer solve)
    {
        lostpropertyService.updateSolve(id,solve);
        return Result.success();
    }
    @NoNeedLogin
    @DeleteMapping("/deleteData/{id}")
    public Result deleteItem(@PathVariable("id") Integer id)
    {
        lostpropertyService.deleteItemdata(id);
        return Result.success();
    }
}
