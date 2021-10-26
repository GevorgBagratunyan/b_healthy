package com.blueteam.tracker.service.crud;

public interface Delete<DTO, ID>{
    DTO delete(ID id);
}
