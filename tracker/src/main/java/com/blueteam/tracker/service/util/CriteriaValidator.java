package com.blueteam.tracker.service.util;

import com.blueteam.tracker.entity.Patient;
import com.blueteam.tracker.exception.search.SearchIllegalArgumentException;
import com.blueteam.tracker.exception.search.SearchNullParametersException;
import com.blueteam.tracker.service.criteria.SearchCriteria;

import java.lang.reflect.Field;

public class CriteriaValidator {

    private CriteriaValidator() {

    }

    public static void validateCriteria(SearchCriteria criteria) {

        Integer limit = criteria.getLimit();
        Integer offset = criteria.getOffset();
        String sort = criteria.getSort();
        String orderByFieldName = criteria.getOrderByFieldName();
        String message =  "limit = " + limit + ", offset = " + offset +
                ", sort = " + sort + ", orderByFieldName = " + orderByFieldName;

        if(limit == null || offset == null) {
            throw new SearchNullParametersException("limit, offset");
        }
        if(sort == null) {
            criteria.setSort("ASC");
        }
        if(orderByFieldName == null) {
            criteria.setOrderByFieldName("id");
        }
        if(limit<0 || offset<0 ) {
            throw new SearchIllegalArgumentException("limit and offset must be grater than or equal to zero");
        }
        if(!sort.equalsIgnoreCase("ASC") && !sort.equalsIgnoreCase("DESC")) {
            throw new SearchIllegalArgumentException(("'sort' must be of two types -> ASC or DESC"));
        }

        //This part of code checks all declared fields in ObservedPatient.class
        Field[] fields = Patient.class.getDeclaredFields();
        boolean fieldIsExists = false;
        for (Field f : fields) {
            String fieldName = f.getName();
            if(orderByFieldName.equalsIgnoreCase(fieldName)) {
                fieldIsExists = true;
            }
        }
        if(!fieldIsExists) {
            throw new SearchIllegalArgumentException("field Name : " + orderByFieldName + " DOES NOT EXIST");
        }
    }
}
