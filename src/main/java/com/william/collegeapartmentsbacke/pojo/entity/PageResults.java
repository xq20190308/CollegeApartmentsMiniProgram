package com.william.collegeapartmentsbacke.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.william.collegeapartmentsbacke.pojo.dto.PageDTO;
import lombok.Data;

import java.util.List;

@Data
public class PageResults<T> {

    @JsonProperty("查询总页数")
    private long pageCount;

    @JsonProperty("存储查询出来的数据")
    private List<T> list;

    @JsonProperty("分页信息数据")
    private PageDTO page;
}
