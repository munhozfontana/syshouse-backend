package org.systemrendas.dto.utils;

import java.util.List;

public class Pagination<T> {

    private List<T> list;

    private int page;

    private int size;

    private Long count;

    public Pagination(List<T> list, int page, int size, Long count) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }

}