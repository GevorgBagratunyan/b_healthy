package com.blueteam.appointment.service.crud;

public interface Delete<DTO, ID>{
    DTO delete(ID id);
}
