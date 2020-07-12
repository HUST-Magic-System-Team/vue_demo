package com.manli.manli_java.model_impl;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageImp1<T> {
    public Integer count;
    public Integer totalPages;
    public Integer pageSize;
    public Integer currentPage;
    public List<T> data;

    private PageImp1() {

    }

    public static PageImp1 fromPage(Page page) {
        PageImp1 pageImp1 = new PageImp1();
        pageImp1.setCount(page.getSize());
        pageImp1.setTotalPages(page.getTotalPages());
        pageImp1.setPageSize(page.getSize());
        pageImp1.setCurrentPage(page.getNumber() + 1); //页码会差1，因为是从0开始的.
        pageImp1.setData(page.getContent());
        return pageImp1;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
