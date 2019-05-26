package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

    private int pageNumber;
    private int pagesAvailable;
    private long totalItems;
    private List<E> pageItems = new ArrayList<>();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public void setPageItems(List<E> pageItems) {
        this.pageItems = pageItems;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public List<E> getPageItems() {
        return pageItems;
    }
}