package com.blueteam.appointment.service.crud;


public interface Read<DTO, ID>{
    DTO get(ID id);
}
