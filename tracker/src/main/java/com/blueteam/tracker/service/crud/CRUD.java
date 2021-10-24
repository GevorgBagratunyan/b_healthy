package com.blueteam.tracker.service.crud;

public interface CRUD<DTO, ID> extends
        Create<DTO>,
        Read<DTO, ID>,
        Update<DTO, ID>,
        Delete<ID>{

    @Override
    void create(DTO dto);

    @Override
    void delete(ID id);

    @Override
    DTO get(ID id);

    @Override
    void update(DTO dto, ID id);

}
