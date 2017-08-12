package com.sy.model.biz.vo;

public abstract class BaseSearchObject<T> {
    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页显示数
     */
    private Integer pageSize = 10;

    private Integer numPerPage = 10;

    private String orderField;

    private String orderDirection = "DESC";

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public abstract T toExample();
}