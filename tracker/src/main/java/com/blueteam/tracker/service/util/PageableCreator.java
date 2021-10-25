package com.blueteam.tracker.service.util;

import com.blueteam.tracker.service.criteria.PageableImp;
import com.blueteam.tracker.service.criteria.SearchCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {

    private PageableCreator() {

    }

    public static Pageable createPageable(SearchCriteria criteria) {
        Integer limit = criteria.getLimit();
        Integer offset = criteria.getOffset();
        String sort = criteria.getSort();
        String orderByFieldName = criteria.getOrderByFieldName();

        Sort srt;
        if (sort.equalsIgnoreCase("ASC")) {
            srt = Sort.by(Sort.Direction.ASC, orderByFieldName);
        } else {
            srt = Sort.by(Sort.Direction.DESC, orderByFieldName);
        }
        return new PageableImp(limit, offset, srt);
    }
}
