package com.blueteam.notifier.service.crud;

public interface Update<DTO, ID>{
    DTO update(DTO dto, ID id);
}
