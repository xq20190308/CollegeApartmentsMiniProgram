package com.william.collegeapartmentsbacke.controller;
import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import com.william.collegeapartmentsbacke.pojo.entity.*;
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

    @NoNeedLogin
    @PostMapping("/uploadItem")
    public AjaxResult saveFile(@RequestHeader("Authorization")String token, @RequestParam("file") List<MultipartFile> file, HttpServletRequest request)
    {
        return AjaxResult.success(fileService.Savefile(token,file,request));

    }

    @PostMapping("/addFound")
    public AjaxResult saveSubmit(@RequestBody Itemdata itemdata)
    {
            lostpropertyService.saveSubmit(itemdata);
            return AjaxResult.success();
    }

    @NoNeedLogin
    @GetMapping("/Getdata/{category}/{page}")
    public AjaxResult SelectData(@PathVariable("category") String category, @PathVariable("page") long page, HttpServletResponse response)
    {
        PageDTO pageDTO= new PageDTO();
        pageDTO.setNowPage(page);
        PageResults<Itemdata> pageResults=lostpropertyService.getItemData(category,pageDTO);
        List<Itemdata> list = pageResults.getList();
        response.setHeader("totalPage", String.valueOf(pageResults.getPageCount()));
        System.out.println("查询数据：" + list);
        System.out.println("查询总数：" +list.size());
        return AjaxResult.success(list);
    }

    @GetMapping("/getMydata/{id}")
    public AjaxResult SelectMydata(@PathVariable ("id") String id)
    {
        List<Itemdata> itemdata= lostpropertyService.getUserItemdata(id);
        return AjaxResult.success(itemdata);
    }
//    @GetMapping("/getydata/{category}")
//    public Result SelectData(@PathVariable("category") String category)
//    {
//        List<Itemdata> itemdata= lostpropertyService.getItemdata(category);
//        return Result.success(itemdata);
//    }

    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody Itemdata itemdata) {
        try {
            lostpropertyService.updateItemdata(itemdata);
            return AjaxResult.success();
        } catch (IllegalArgumentException e) {
        log.error("Invalid argument for update status", e);
        return AjaxResult.error("Invalid argument for update status");
        } catch (Exception e) {
        log.error("Failed to update status", e);
        return AjaxResult.error("Failed to update status");
        }
    }

   @PostMapping("/updateData/{id}")
    public AjaxResult updateData(@PathVariable("id") Integer id, Integer solve)
    {
        lostpropertyService.updateSolve(id,solve);
        return AjaxResult.success();
    }
    @NoNeedLogin
    @DeleteMapping("/deleteData/{id}")
    public AjaxResult deleteItem(@PathVariable("id") Integer id)
    {
        lostpropertyService.deleteItemdata(id);
        return AjaxResult.success();
    }
}
