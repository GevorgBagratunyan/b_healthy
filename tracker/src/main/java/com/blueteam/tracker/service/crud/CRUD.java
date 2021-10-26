package com.blueteam.tracker.service.crud;

public interface CRUD<DTO, ID> extends
        Create<DTO>,
        Read<DTO, ID>,
        Update<DTO, ID>,
        Delete<DTO, ID>{

    @Override
    DTO create(DTO dto);

    @Override
    DTO delete(ID id);

    @Override
    DTO get(ID id);

    @Override
    DTO update(DTO dto, ID id);

}
