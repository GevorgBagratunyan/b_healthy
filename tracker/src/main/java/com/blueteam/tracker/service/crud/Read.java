package com.blueteam.tracker.service.crud;


public interface Read<DTO, ID>{
    DTO get(ID id);
}
