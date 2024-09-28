package com.william.collegeapartmentsbacke.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDTO {
    @JsonProperty("当前页数，即需要查询的页码")
    private Long nowPage;

    @JsonProperty("每页数据条数")
    private Long eachPageCount;

    @JsonProperty("数据总条数")
    private Long dataCount;

    @JsonProperty("总页数，即数据总条数除以每页数据条数的结果")
    private Long PageCount;

    @JsonProperty("分页查询时需要从哪一条数据开始查询")
    private Long startIndex;

    @JsonProperty("排序字段和排序方式")
    private String orderBy;

    public PageDTO() {

    }
    public PageDTO(long nowPage, long eachPageCount, long dataCount, long pageCount) {
        this.nowPage = nowPage;
        this.eachPageCount = eachPageCount;
        this.dataCount = dataCount;
        this.PageCount = pageCount;
    }
}
