package com.blueteam.tracker.service.crud;

public interface Update<DTO, ID>{
    void update(DTO dto, ID id);
}
