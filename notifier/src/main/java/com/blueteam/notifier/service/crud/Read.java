package com.blueteam.notifier.service.crud;


public interface Read<DTO, ID>{
    DTO get(ID id);
}
