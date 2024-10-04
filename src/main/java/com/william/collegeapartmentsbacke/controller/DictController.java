package com.william.collegeapartmentsbacke.controller;


import com.william.collegeapartmentsbacke.common.annotations.NoNeedLogin;
import com.william.collegeapartmentsbacke.pojo.entity.AjaxResult;
import com.william.collegeapartmentsbacke.pojo.entity.DictItem;
import com.william.collegeapartmentsbacke.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //查询字典键值
    @NoNeedLogin
    @PostMapping("/selectByLabel")
    public AjaxResult searchDicts(@RequestBody String type)
    {
        log.info("type:{}", type);
        List<DictItem> dictItems = dictService.searchDicts(type);
        return AjaxResult.success(dictItems);
    }

}

