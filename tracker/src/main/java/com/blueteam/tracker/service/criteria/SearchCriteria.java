package com.blueteam.tracker.service.criteria;


public class SearchCriteria {
    private Long id;
    private Integer limit;
    private Integer offset;
    private String sort; // ASC or DESC
    private String orderByFieldName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderByFieldName() {
        return orderByFieldName;
    }

    public void setOrderByFieldName(String orderByFieldName) {
        this.orderByFieldName = orderByFieldName;
    }
}
