package com.blueteam.authentication.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetBasedPageRequest implements Pageable {
    private Integer limit;
    private Integer offset;
    private Sort sort;


    public OffsetBasedPageRequest(Integer limit, Integer offset, Sort sort) {
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public OffsetBasedPageRequest(Integer limit, Integer offset) {
        this(limit, offset, Sort.unsorted());
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
        return new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() + getPageSize()), sort);
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();

    }

    public Pageable previous() {
        return hasPrevious() ?
                new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() - getPageSize()), sort) : this;
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(getPageSize(), 0, sort);
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
