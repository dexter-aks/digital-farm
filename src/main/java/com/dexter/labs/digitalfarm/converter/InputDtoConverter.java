package com.dexter.labs.digitalfarm.converter;

public interface InputDtoConverter<DTO, Entity> {

    public Entity convert(DTO dto);
}
