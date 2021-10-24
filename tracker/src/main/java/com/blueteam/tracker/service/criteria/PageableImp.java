package com.blueteam.tracker.service.criteria;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableImp implements Pageable {

    private Integer limit;
    private Integer offset;
    private Sort sort;


    public PageableImp(Integer limit, Integer offset, Sort sort) {
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public PageableImp(Integer limit, Integer offset) {
        this(limit,offset,Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageableImp(getPageSize(), (int) (getOffset() + getPageSize()),sort);
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();

    }

    public Pageable previous() {
        return hasPrevious() ?
                new PageableImp(getPageSize(), (int) (getOffset() - getPageSize()), sort): this;
    }

    @Override
    public Pageable first() {
        return new PageableImp(getPageSize(), 0, sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
