package com.blueteam.notifier.service.crud;

public interface Delete<DTO, ID>{
    DTO delete(ID id);
}
